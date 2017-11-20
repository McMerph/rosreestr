# rosreestr
This application facilitates work with flat excerpts from rosreestr.

## JVM
Build jar:

    mvn clean package
    
Run jar:

    cd /target/jfx/app/
    {artifactId}-{version}-jfx.jar


## Native
Build native:

    mvn jfx:native

Run native:

    cd /target/jfx/native/
    {artifactId}-{version}/{native}