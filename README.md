[![CircleCI](https://circleci.com/gh/pawnjester/D2-news-application.svg?style=svg)](https://circleci.com/gh/pawnjester/D2-news-application/tree/develop)
# News Application
This is an application that consumes Newyork times API. This application is built using Kotlin and MVVM architecture

- [Home Articles](https://api.nytimes.com/svc/topstories/v2/home.json)
- [Food Articles](https://api.nytimes.com/svc/topstories/v2/food.json)
- [Fashion Articles](https://api.nytimes.com/svc/topstories/v2/fashion.json)

# To Get Started
- Clone this [repository](https://github.com/pawnjester/D2-news-application.git)
- Get an api key from [Key](https://api.nytimes.com/)
- Include the api key in the `build.gradle` file and replace the ******* with the key

# App Features
1. Users can view the top stories from categories like headline, fashion and food from the Newyork times
2. Users can share the personalized message with their contacts name on their phone.
3. Offline reading.

## Download the Application
The application can be accessed using this [link](http://www.droidbin.com/p1d2gh0fqld0r1j4l1efti579jo3)


## Application Architechure
This application was written following the MVVM (Model-view-viewModel) architechure. The main reason behind this choice is because
it enables us take advantage of the Google architechure components/ [Android Jetpack](https://developer.android.com/jetpack/docs/guide) components like the viewModel, Room,
livedata and Databinding which was all evident in this application. I also made use of other libraries to ensure a seemless flow. They are listed below.

The application follows best practices such as the repository pattern for abstracting classes and Dagger 2 For Dependency Injection.

## Libraries/Tools Used
- Use [Retrofit](https://square.github.io/retrofit/) for networking
- Use [RxJava](http://www.vogella.com/tutorials/RxJava/article.html) observing data changes and making asynchronous calls.
- Use [Picasso](http://square.github.io/picasso/) for image loading
- Use [Room](https://developer.android.com/topic/libraries/architecture/room) for storing data
- Use [Content Provider](https://developer.android.com/guide/topics/providers/contacts-provider) to allow the user view the contacts on a phone to allow them share a message
- Use [Databinding](https://developer.android.com/topic/libraries/data-binding/)
- Use [Dagger 2](https://github.com/google/dagger)
- Language used - Kotlin (being the official language of Google)

## Test
1. For unit test `./gradlew test`
2. For ui test `./gradlew connectedAndroidTest`


