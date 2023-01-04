job("Build, test and create docker image") {
    container("amazoncorretto:17-alpine") {
        shellScript {
            content = """
            echo Build and run tests...
            ./gradlew clean build
            echo Copy build dir to share path
            cp -rv build/libs ${"$"}JB_SPACE_FILE_SHARE_PATH
        """
        }
    }
    // both 'host.shellScript' and 'host.dockerBuildPush' run on the same host
    host("Build artifacts and a Docker image") {
        // Gradle build creates artifacts in ./build
        dockerBuildPush {
            // Note that if Dockerfile is in the project root, we don't specify its path.
            // We also imply that Dockerfile takes artifacts from ./build and puts them to image
            // e.g. with 'ADD /build/app.jar /root/home/app.jar'
            this@host.shellScript {
                content = """
                    echo Copy build dir from share path
                    cp -r ${"$"}JB_SPACE_FILE_SHARE_PATH
                """.trimIndent()
            }
            val spaceRepo = "horvathzsolt.registry.jetbrains.space/p/containers/containers/personalregistry"
            // image tags for 'docker push'
            tags {
                +"$spaceRepo:1.0.${"$"}JB_SPACE_EXECUTION_NUMBER"
                +"$spaceRepo:latest"
            }
        }
    }
}