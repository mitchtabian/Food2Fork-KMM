# <img src="https://github.com/mitchtabian/Food2Fork-KMM/blob/master/assets/food2fork_logo.png?raw=true" alt="Food2Fork App Icon" width="50" height="50"> Food2Fork Recipe App

This is the codebase for a Kotlin Multiplatform Mobile course.
[Watch the course](https://codingwithmitch.com/courses/kotlin-multiplatform-mobile/)

# List to Detail screen
<img src="assets/list_detail_demo.gif" width="25%">

# Searching
<img src="assets/search_recipes_demo.gif" width="25%">


# Architecture
#### Shared Components
1. Ktor (Network Client)
1. SQL Delight (Caching Client)
1. Kotlinx.datetime

#### Android Specific Components
1. Jetpack Compose
1. Jetpack Compose Navigation
	- (Single activity, zero fragments)
1. Accompanist Coil
1. Hilt
    - I decided to use AAC ViewModel because it gives so much state management stuff for free. Maybe in the future a shared viewmodel will be more practical. I'll talk about this in detail in the course.
    - See this tweet thread if you want to do some reading: https://twitter.com/ianhlake/status/1388517293005574144

#### iOS Specific Components
1. SwiftUI


<img class='header-img' src="https://github.com/mitchtabian/Food2Fork-KMM/blob/master/assets/clean_architecture_kmm.png?raw=true" />
<br>

# Android Studio Version
The project dependencies have been updated and you can use Android Studio Bumblebee (2021.1.1) Canary 3. Download from the archive [here](https://developer.android.com/studio/archive).

# Kotlin Multiplatform Talks
1. Mitch Tabian
    1. [Is Kotlin Multiplatform Ready for Production?](https://www.youtube.com/watch?v=L8Xq15NTuCc)
    1. [KMM - Fragmented Podcast](https://fragmentedpodcast.com/episodes/210/)
1. Daniele Barconcelli
	- https://www.youtube.com/watch?v=J3x7_HhrvO8
1. Ekaterina Petrova
	- https://www.youtube.com/watch?v=PW-jkOLucjM&ab_channel=JetBrainsTV
	- https://www.youtube.com/watch?v=mdN6P6RI__k
1. Dmitry Savvinov
	- https://www.youtube.com/watch?v=5QPPZV04-50&ab_channel=JetBrainsTV
1. Kevin Galligan
	- Kotlin conf 2019
		- https://www.youtube.com/watch?v=oxQ6e1VeH4M&ab_channel=JetBrainsTV
1. Ben Asher and Alec Strong
	- kotlin conf 2019
		- https://www.youtube.com/watch?v=je8aqW48JiA&ab_channel=JetBrainsTV









