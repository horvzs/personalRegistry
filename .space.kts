job("Build, test and publish Docker") {
    job("Build and run tests") {
        gradlew("amazoncorretto:17-alpine", "build")
    }
    host("Build and push a Docker image") {
        dockerBuildPush {
            // by default, the step runs not only 'docker build' but also 'docker push'
            // to disable pushing, add the following line:
            // push = false

            // path to Docker context (by default, context is working dir)
            context = "docker"
            // path to Dockerfile relative to the project root
            // if 'file' is not specified, Docker will look for it in 'context'/Dockerfile
            file = "docker/Dockerfile"
            // build-time variables
            args["HTTP_PROXY"] = "http://10.20.30.2:1234"
            // image labels
            labels["vendor"] = "horvathzsolt"
            // to add a raw list of additional build arguments, use
            // extraArgsForBuildCommand = listOf("...")
            // to add a raw list of additional push arguments, use
            // extraArgsForPushCommand = listOf("...")
            // image tags
            val spaceRepo = "horvathzsolt.registry.jetbrains.space/p/containers/containers/personalregistry"
            // image tags for 'docker push'
            tags {
                +"$spaceRepo:1.0.${"$"}JB_SPACE_EXECUTION_NUMBER"
                +"$spaceRepo:latest"
            }
        }
    }
}