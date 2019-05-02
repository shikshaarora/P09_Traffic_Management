# Traffic Management - Face Recognition/Number Plate Detection With Android & OpenCV
TMS is an open source android application that does face recognition using OpenCV and Number plate Detection using Android-OCR, Originally made for Traffic officers who are working on roads, TMS can be used to replace the old method of Challaning traffic offenders(which was done by filling a paper-challan form, that required a lot of effort to store them safe from any disaster)


### Credits
- [https://github.com/trafficmanagement/](https://github.com/trafficmanagement/)


### Video Demo of Face Recognition
...to be added


### Screenshots
...to be added

1. **Home page** - For switching between training and testing
2. **Home > Training (enter ID)** - Enter ID for the train images
3. **Home > Training (enter ID) > Capture** -  Capture train image for the ID
4. **Home > Recognition** - Recognize faces
5. **Home > Recognition > Review** - Review capture results (Optionally push to database)


**How to open**

- Clone this repository
- Open project in android studio
- Compile, install the Apk
- For Face Recognition: Go to training, set an ID and capture a face to train. Repeat this a couple of times with different people and IDs
- Go to recognition, click scan and try to capture everyone in the video stream. The detected faces will be recognized and name of person will be shown.
- For Number Plate Detection: Go to Number Plate Detection, Either "click a picture" or "Choose from gallery" picture of vechile, and then the number plate characters will automatically be generated.
- To Challan a Violator: go to Challan, enter his licence number, 
                  if he has previous records, all his personal details will be shown,
                  else he might not have any previous records,
   Enter all the details in the form along with a picture of his Driving Licence Card and register him into database.
- All the Above Details extracted(Name,Licence Plate,Driving Licence Number) can be used to search the existing database to find any previous record of that person.

### Known Issues & TODO
- Face recognition is not accurate. Far from it
- Recognition model gets created each time "Recognize" tab is clicked. Slows down as number of training images increase

### Directories
- **/sdcard/facerecogOCV** - Training images

### License
MIET
