# Dogbreeds
A simple demo project for The Dogbreeds DB based on <b>MVVM clean architecture</b>.

<img src="https://github.com/irahulyadav/DogBreeds/blob/master/device-2019-05-09-164535.png" width="200" style="max-width:100%;">   <img src="https://github.com/irahulyadav/DogBreeds/blob/master/device-2019-05-09-164600.png" width="200" style="max-width:100%;">  <img src="https://github.com/irahulyadav/DogBreeds/blob/master/device-2019-05-09-164615.png" width="200" style="max-width:100%;"></br></br>

#### App Features
* Users can view list Dog Breeds.
* Users can search for Dog Breeds
* Users can click on any Dog Breeds to see the images


#### App Architecture 
Based on mvvm architecture and repository pattern.

<img src="https://github.com/irahulyadav/DogBreeds/blob/master/flow-chart.png" width="500" style="max-width:500%;">
 
 #### The app includes the following main components:

* A local database that servers as a single source of truth for data presented to the user. 
* A web api service.
* A repository that works with the database and the api service, providing a unified data interface.
* A ViewModel that provides data specific for the UI.
* The UI, which shows a visual representation of the data in the ViewModel.
* Unit Test cases for API service


#### App Packages
* <b>data.local</b> - contains 
    * <b>remote</b> - contains the api classes to make api calls to server, using Retrofit. 
    * <b>db</b> - contains the db classes to cache network data.
* <b>di</b> - contains dependency injection classes, using Dagger2.
* <b>viewmodel</b> - contains classes needed to display Activity and Fragment.
* <b>ui</b> - contains classes needed to display Activity and Fragment.
* <b>repository</b> - contains the repositiry classes to cache network data adn db operation.


#### App Specs
* Minimum SDK 24
* Java based (Kotlin version coming soon!)
* MVVM Architecture
* Android Architecture Components (LiveData, Lifecycle, ViewModel, Room Persistence Library, ConstraintLayout)
* [AndroidX](https://developer.android.com/jetpack/androidx) androidx support libraries
* [RxJava2](https://github.com/ReactiveX/RxJava) for implementing Observable pattern.
* [Dagger 2](https://google.github.io/dagger/) for dependency injection.
* [Retrofit 2](https://square.github.io/retrofit/) for API integration.
* [Okhhtp3](https://github.com/square/okhttp) for implementing interceptor, logging and mocking web server.
* [Mockito](https://site.mockito.org/) for implementing unit test cases
* [Picasso](http://square.github.io/picasso/) for image loading.
* [Lottie](https://github.com/airbnb/lottie-android) lottie for animation
* [CircleImageView](https://github.com/hdodenhof/CircleImageView) Thanks hdodenhof for CircleImageView
