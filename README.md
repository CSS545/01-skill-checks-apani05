## 1. Document that contains:
### - Various approaches to storage management on your platform of choice
### - Pros/cons of each approach for your project

----------------------------------------------------------------
#### There are multiple ways for storage management on Android. Android utilizes a file system that’s identical to other platforms w.r.t disk-based file system. There are several options to save our app data. 

   1. App-Specific Storage: In this approach, the stored files are in app use which could be found in dedicated directories in an internal storage volume or different dedicated directories in external storage. Access method for internal storage include getFilesDir() or getCacheDir() and getExternalFilesDir() or getExternalCacheDir() for external storage. 
        1. Pros: 
            * No need for permissions for internal and external (if devices run on Android 4.4 or higher) 
        2. Cons:
            * No other apps can access these. 
            * Files are removed on app uninstall 

   2. Media (Shared Storage): In this approach, the stored files are in app use intends to share with other apps which include media, documents, videos and other files. Access methods is MediaStore API. 
        1. Pros: 
            * Other apps can read the data when READ_EXTERNAL_STORAGE permission is granted
        2. Cons:
            * Permissions are required to read/write data into external storage which could be complex for different device types. 
            * Files are restored after uninstall. 

   3. Documents (Shared Storage): This approach accesses the shared network framework to read/write the contents into the file system. We can use this system to share/download files from different network framework. The access API is Storage Access Framework.  
        1. Pros: 
            * No need of user/system level permissions for internal and external (if devices run on Android 4.4 or higher) 
            * Other apps can access the data from the system file picker. 
        2. Cons:
            * Even though there is no permission needed to access the files, the user still can’t create a resource with existing file data using ACTION_CREATE_DOCUMENT
            * Even after calling takePersistableUriPermission(), the app doesn’t retain access to the URI if the relevant file is moved/deleted. In those cases, we will need to request permission again to regain access to the URI
            * Files are restored even after uninstall 

   4. App Preferences Storage: In this approach, the stored data are primitive data in key-value pairs and are also private.  We can create a new shared preference file or access an existing one by calling getSharedPrefernces() or getPreferences() methods. The API accessed would be the Jetpack preferences library. 
        1. Pros: 
            * No need of permissions for internal and external storage
            * Other apps can access the data 
            * Files are removed on app uninstall 

        2. Cons:
            * If the app requires us to sign in, we will want to store the data and distribute it among other devices connected to the network. If there are configurations that are device specific each user has to separate settings making this solution not an ideal one. 
   5. Database Storage: In this approach, the data can be accessed from a database server and read/write operations could be implemented. Room persistence library is required to access the server. My team is inclined towards using this method of storage while we build our final project. 
        1. Pros: 
            * Data is cached in the device which would be very useful if the device is offline from the network and the users can still browse the content while they are offline. 
            * Compile time verification of SQL queries which allows data validation
            * Files are removed on app uninstall 
            * Other apps can access this data storage. 
        2. Cons:
            * While the transactions from the database storage are great, there can be an additional cost of network transfers, latency and file exchange from the system. 

## 1. App that demonstrates:
###  - Store/Load a media item locally (e.g. music, video, image)
###  - Store/Load user settings locally

-------------------------------------------------------------------------------
<b>Application Name - Beauty Cam
  1. Stores and loads an image from Gallery.
  2. Asks for permission to access photos and media folder on the device and then stores user preference in the settings.</b>
  
#### Screensots

<img src="https://user-images.githubusercontent.com/114898227/197447618-bfce748b-1a03-4996-a4a2-4783b601bec0.jpg" width="300"> &nbsp; &nbsp; &nbsp; <img src="https://user-images.githubusercontent.com/114898227/197447584-a4fca83d-9141-4e01-a6e3-2b2d02cb3b89.jpg" width="300"> &nbsp; &nbsp; &nbsp; <img src="https://user-images.githubusercontent.com/114898227/197447613-6a6b1a6a-9fa1-48e0-9dee-9ef9447f68f3.jpg" width="300">
<img src="https://user-images.githubusercontent.com/114898227/197447615-f650c8d1-b42c-421a-9d4d-3d90509cb12c.jpg" width="300"> &nbsp; &nbsp; &nbsp; 
<img src="https://user-images.githubusercontent.com/114898227/197447619-b6430ef4-3b35-44b8-b8ad-2abe307ccd7a.jpg" width="300"> &nbsp; &nbsp; &nbsp; <img src="https://user-images.githubusercontent.com/114898227/197447607-73262bb5-c4af-4a10-a3c2-26e7f3f63fe7.jpg" width="300"> <img src="https://user-images.githubusercontent.com/114898227/197447544-4b94fd6c-5107-40f3-bba0-17576f80d4c8.jpg" width="300">
