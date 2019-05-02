# Traffic Management - Face Recognition/Number Plate Detection With Android & OpenCV
TMS is an open source android application that does face recognition using OpenCV and Number plate Detection using Android-OCR, Originally made for Traffic officers who are working on roads, TMS can be used to replace the old method of Challaning traffic offenders(which was done by filling a paper-challan form, that required a lot of effort to store them safe from any disaster)


### Credits
- [https://github.com/shikshaarora](https://github.com/shikshaarora)
- [https://github.com/Nivedita29](https://github.com/Nivedita29)
- [https://github.com/trafficmanagement/](https://github.com/trafficmanagement/)


### Video Demo
...to be added


### Screenshots
<img src="https://github.com/trafficmanagement/P09_traffic_management/blob/master/docs/static/1.jpg?raw=true" width="60%"/>
<img src="https://github.com/trafficmanagement/P09_traffic_management/blob/master/docs/static/2.jpg?raw=true" width="60%"/>
<img src="https://github.com/trafficmanagement/P09_traffic_management/blob/master/docs/static/3.jpg?raw=true" width="60%"/>
<img src="https://github.com/trafficmanagement/P09_traffic_management/blob/master/docs/static/4.jpg?raw=true" width="60%"/>

***Face Recognition:***
1. **Home page** - For switching between face recognition and number plate detection.
2. **Home > ADD NEW ENTRY (enter name)** - Enter name of person standing in front of camera.
3. **Home > ADD NEW ENTRY (enter name) > Capture** -  Capture image.
4. **Home > Recognition** - Recognize faces
5. **Home > Recognition > Submit** - Display name of person.

***Number Plate Detection***
1. **Home page** - For switching between face recognition and number plate detection.
2. **Home > Number Plate Detection > Choose_from_galary/Take_a_picture**- Insert picture of the vehicle
3. **Home >  Number Plate Detection > Choose_from_galary/Take_a_picture > Textbox**-  Extract the Characters from vehicle number plate and display it.
4. **Home > Number Plate Detection > Challan** - Fill the details of violator along with picture of his/her Driving Licence Card.
5. **Home > Number Plate Detection > Database** - Search the database using any known identity of the violator to get his pervious records.

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
