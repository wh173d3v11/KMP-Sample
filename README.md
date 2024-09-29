
# KMP Multiplatform App 🚀
This is a Kotlin Multiplatform project targeting Android, iOS, Desktop.
This Kotlin Multiplatform (KMP) project showcases how to build a truly cross-platform application using Jetpack Compose, SwiftUI, Ktor, and MVVM architecture. The app supports Android, iOS, and Desktop platforms, making use of the latest technologies like Flows for state management, and network calls using Ktor. Additionally, it implements efficient image loading across all platforms.

#  Outputs

<img width="1512" alt="Screenshot 2024-09-29 at 5 49 39 PM" src="https://github.com/user-attachments/assets/b091b00d-575b-497f-a22c-f125c8d610e5">
<img width="1512" alt="Screenshot 2024-09-29 at 5 50 30 PM" src="https://github.com/user-attachments/assets/87bf7136-da6c-41bf-9609-d7faa62c3658">
<img width="1512" alt="Screenshot 2024-09-29 at 5 50 56 PM" src="https://github.com/user-attachments/assets/7ff08c1e-b73f-4d09-8e75-b70575bd21be">

# Features ✨
* Mixed UIs - Jetpack Compose and Swift UI.
* Jetpack Compose for Android, iOS, and Desktop UI
* Partial SwiftUI for iOS UI (bottomsheet only)
* Ktor for making network calls
* MVVM architecture
* Image loader for cross-platform image handling
* Shared business logic for all platforms

# Tech Stack 🛠️
* **[Kotlin Multiplatform (KMP)](https://kotlinlang.org/docs/multiplatform.html)**: Shared codebase across platforms
* **[Jetpack Compose](https://developer.android.com/jetpack/compose)**: Declarative UI framework for Android
* SwiftUI: Declarative UI framework for iOS
* Ktor: Networking library for making HTTP requests
* MVVM Architecture: Ensures separation of concerns and easy scalability
* **[Compose ImageLoader](https://github.com/qdsfdhvh/compose-imageloader)**: For image loading on Android and iOS respectively

# Usage 🔥
The app demonstrates:

* Fetching data from network using Ktor.
* Displaying data in Jetpack Compose, SwiftUI.
* Image loading optimized for each platform.

# Contributing 💡
Feel free to submit issues, fork the repository, and send pull requests. Contributions are always welcome!

# Project Structure 📂
* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…
