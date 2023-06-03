# Photos_Challange
This App is using flicker api to list images and open it in seprate screen

## The Following Pattern
We used MVVM pattern while developing
The separation of the code in MVVM is divided into View, ViewModel and Model:
- View is the collection of visible elements, which also receives user input. This includes user interfaces (UI), animations and text. The content of View is not interacted with directly to change
what is presented.
- ViewModel is located between the View and Model layers. This is where the controls for interacting with View are housed, while binding is used to connect the UI elements in View to the controls in
ViewModel.
- Model houses the logic for the program, which is retrieved by the ViewModel upon its own receipt of input from the user through View. In the model we follow **Repo Pattern** to handle getting data

## third party used
- Glide: Show image from url
- Paging 3: Handle pagination
- room: Handle caching data
- retrofit: Handle calling aoi
- koin: For DI
- Coroutines: Handle threads task
- PhotoView: Handle zoom

## The App contains five pagakes
- photo full screen pacakge -> responsable for show selecred image in full screen
- photo list pacage -> responable for calling list images by using remoteMediator
- shared -> contains models that used by different packages
  - databinding -> contains custom data binding adaterp
  - koin -> define app module and room module
  - model -> Contain share model for error
  - Network -> retrofit client and web service
  - room -> contains Daos and DB

## Not Finished
- Handle issue when rotate device in photo full screen 
- Write UI/Unit test
  - I faced an issue with runing testing still investgation on it


 
