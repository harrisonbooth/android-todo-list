# Trevies ToDo
### Task tracker to-do list.
First large scale android/java project.
  - Java
    * Task class, each has a headline, description, completion status and priority status.
    * Tasklist class, which holds tasks in an arraylist.
  - Android (Saved preferences)
    * Saved preference classes for tasklist and for currently selected theme.
    * Tasklist is converted into JSON using the GSON library, which is then saved as a string.
    * Currently selected theme is a string, so can be saved as they are.
    * Both have methods to set them, and to get them.
  - Android (Activities) **Need large refactor**
    * Active task list activity: displays incomplete tasks, allowing them to be selected to display details, and menu options for selecting themes, viewing the archive, and creating new tasks.
    * Archive task list activity: display completed tasks, allowing them to be selected to display details.
    * New task activity: allows information to be entered into EditTexts and a save button exists, which saved the input information on press.
    * Task details activity: shows task headline, description, completion status and if the task is a priority. Menu allows selected task to be toggled between completion statuses, deleted, and moved back to the active task list. The back button brings the user to the either the active tasklist or archive, depending on where they came from.
    * Themes selection menu: displays all themes, allowing them to be selected. Upon selecting a theme, it will be saved as the currently selected theme and will load on every activity.
    
---

### Instructions to run
  - Ensure to have android studio installed.
  - Open the cloned folder in android studio.
  - Build the project in an emulator or android device which runs Android API 16 or later.
