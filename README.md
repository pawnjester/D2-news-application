[![CircleCI](https://circleci.com/gh/pawnjester/D2-news-application.svg?style=svg)](https://circleci.com/gh/pawnjester/D2-news-application/tree/develop)
# News Application
This is an application that consumes Newyork times API. This application is built using Kotlin and MVVM architecture

- [Home Articles](https://api.nytimes.com/svc/topstories/v2/home.json)
- [Food Articles](https://api.nytimes.com/svc/topstories/v2/food.json)
- [Fashion Articles](https://api.nytimes.com/svc/topstories/v2/fashion.json)

# To Get Started
- Clone this [repository](https://github.com/pawnjester/D2-news-application.git)
- Get an api key from [Key](https://api.nytimes.com/)
- Include the api key in the `build.gradle` file

# App Features
1. Users can view the top stories from categories like headline, fashion and food from the Newyork times
2. Users can share the personalized message with their contacts
3. Offline reading.


## Libraries Used
- Use [Retrofit](https://square.github.io/retrofit/) for networking
- Use [RxJava](http://www.vogella.com/tutorials/RxJava/article.html) observing data changes and making asynchronous calls.
- Use [Picasso](http://square.github.io/picasso/) for image loading
- Use [Room](https://developer.android.com/topic/libraries/architecture/room) for storing data
- Use [Content Provider](https://developer.android.com/guide/topics/providers/contacts-provider) to allow the user view the contacts on a phone to allow them share a message
- Use [Databinding](https://developer.android.com/topic/libraries/data-binding/)

## Test
1. For unit test `./gradlew test`
2. For UI test, run `./gradlew connectedAndroidTest`

