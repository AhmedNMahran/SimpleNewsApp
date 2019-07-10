# SimpleNewsApp
A simple app that gets news from https://newsapi.org in a list and shows details when item clicked
#Language and libraries
The app is written in Kotlin and Java, it uses the power of coroutines, Rertofit and Gson
## libraries used in this app:
1. [Gson](https://github.com/google/gson) - for Json parsing
2. [Retrofit](https://square.github.io/retrofit/) - for Http requests handling
3. [coroutines](https://github.com/Kotlin/kotlinx.coroutines) - for async calls handling
4. [Picasso](https://square.github.io/picasso/) - to view images from urls.

#Note
you need to get an API_KEY from [News API](https:newsapi.org) and put it in the value of **API_KEY** in **NewsApiService.kt** class, otherwise the app will not work

#Architecture
The project uses custom architecture based on MVVM

#Note
the app uses minimal designs and ideas for the sake of simplicity, it could have been better and I could have added more features like, swipe to refresh, search and more.
