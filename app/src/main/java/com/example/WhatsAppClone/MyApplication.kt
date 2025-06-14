import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by shreyasrivastava on 14.02.2025.
 *
 * Description: Serves as a base for maintaining the global application state ex. Data or settings that needs to be maintained throughout the entire life cycle of the application.
 * Not created by default but essential for initializing tasks such as setting up DI frameworks or initializing libraries.
 */
@HiltAndroidApp
class MyApplication : Application() {
    // Class implementation
}