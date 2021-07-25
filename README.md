# SpineStream

SpineSteam is an asynchronous message-passing android lib based on the Event-Driven Architecture. With SpineStream running in your project you can just publish or subscribe to receive an event from any component, without worrying about the creation of the channels or where the event is sending. Just plug and play, subscribing and publishing.

This is a study project and the creation process of the first version (0.1.0) is documented [here]().

## Set up

Add the jitpack maven url in your root build.gradle:
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Add SpineStream as an dependency:
```
dependencies {
    implementation 'com.github.cstiano:spine-stream:0.1.0'
}
```

## Using

## References

Take a look in these related projects that inspired the creation of this lib.