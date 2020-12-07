./gradlew jacocoTestReport coveralls
bash <(curl -s https://copilot.blackducksoftware.com/ci/travis/scripts/upload)
./cc-test-reporter after-build --exit-code $TRAVIS_TEST_RESULT