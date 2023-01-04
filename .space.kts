job("Build, test and create docker image") {
    // both 'host.shellScript' and 'host.dockerBuildPush' run on the same host
    host("Build artifacts and a Docker image") {
        shellScript {
            content = """
                chmod u+r+x ./generateArtifacts.sh
                ./generateArtifacts.sh
            """
        }
        // Gradle build creates artifacts in ./build
        dockerBuildPush {

            // Note that if Dockerfile is in the project root, we don't specify its path.
            // We also imply that Dockerfile takes artifacts from ./build and puts them to image
            // e.g. with 'ADD /build/app.jar /root/home/app.jar'
            val spaceRepo = "horvathzsolt.registry.jetbrains.space/p/containers/containers/personalregistry"
            // image tags for 'docker push'
            tags {
                +"$spaceRepo:1.0.${"$"}JB_SPACE_EXECUTION_NUMBER"
                +"$spaceRepo:latest"
            }
        }
    }
}
