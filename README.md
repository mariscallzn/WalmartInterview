# WalmartInterview
Basic application displaying a list of products from an Api


Architecture perspective

- 1: Fully developed in Kotlin
- 2: Clean Architecture 
- 3: AndroidX (Supporting old versions of Android) & Jetpack architecture components
- 4: MVVM utilizing LiveData (Google's observables with life cycle awareness) 
- 5: Dependency Injection (Dagger 2 in a Android fashion)
- 6: Android Navigation Component
- 7: OkHttp & Retrofit libraries
- 8: RxJava/RxKotlin (as a way to show how to bridge Google's components with third party libraries)
- 9: Timber, third party library for debugging purpose

UI perspective
- Multi row Adapter (The ability to have more than one layout in a single List)
- Layout actions isolation (Each item on the list handles it's unique action independently)
- Drawer Layout
- Product detail displayed as Gmail does.
- Inner navigation in webviews
- Launch external links
- Pagination in Products list
- Toolbar and Drawer operates under Navigation Component
- Glide third party library for image processing
