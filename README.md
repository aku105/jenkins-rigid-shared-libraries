# Jenkins Shared Libraries - Whole pipeline as shared library

Various shared libraries for generic B&amp;R of various archtypes. Maven and Python (half-baked) covered.
All you will need to do is add this repo as shared library in Jenkins and call `mavenBnR()` or `pythonBnR()` in the Jenkins pipeline.

Good when multiple projects with same lifecycle, e.g. microservices that comprise a bigger service.

Bad when different projects do unique steps across them.
