ANDRIOD CODE:
In this folder, the source code for the mobile code is present. The apk file can also be found in this build folder. 

Python Notebooks:
There are two python notebooks in this folder. "Pre-processing.ipynb" has all the implementations. 
The Tasks include: 
1. Retreiving requests from cloud and downloading the input video. 
2. Frame Extraction
3. Posenet
4. Segmentation
5. Cropping Algorithm
6. CNN model prediction
7. Send the result to Cloud(AWS S3)

"CNN_Model.ipynb" has the training process of CNN model. The tasks shown in this are:
1. Downloading the dataset from Kaggle
2. Exporting the data into lists. Reading the image file via opencv and performing data processing
3. BUilding and training the model across the specified batch size and epochs
4. Save the weights into H5

CNN MODEL:

This folder has the weights and architecture of the CNN model in form of .h5 and .json
