# SpineStream

SpineSteam is an asynchronous message-passing android lib based on the Event-Driven Architecture. With SpineStream running in your project you can just publish or subscribe to receive an event from any component, without worrying about the creation of the channels or where the event is sending. Just plug and play, subscribing and publishing.

This is a study project and the creation process of the first version (0.1.0) is documented [here](https://uiracode.com/event-driven-lib-with-coroutines).

## Import the dependency

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

## Usage

SpineStream supports [data classes](https://kotlinlang.org/docs/data-classes.html) as events. The following example shows the UserInfo data class being published through the SpineStream, passing the event between different components.

```kotlin
data class UserInfo(val name: String, val email: String)
```

```kotlin
// MainActivity

 override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    SpineStream.subscribe<UserInfo> { event ->
        Log.d(TAG, "Received user: $event")
    }

}
```

```kotlin
// UserService

override fun onStart(intent: Intent?, startId: Int) {
    super.onStart(intent, startId)
    
    SpineStream.publish(userInfo)
    
}
```

## References

Take a look at these related projects that inspired the creation of this lib.

- [EventBus](https://github.com/greenrobot/EventBus)
- [EventHub](https://github.com/deva666/EventHub)
- [Broker](https://github.com/adrielcafe/Broker)