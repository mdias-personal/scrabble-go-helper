#!/bin/bash

if ! ./gradlew spotlessCheck; then
	echo "spotless check failed. appyling spotless..."
	./gradlew spotlessApply
	if [ -d ".git/" ]; then
		git add $(git diff --name-only)
	fi
fi
