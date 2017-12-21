# Accelerated-Android-Vision
This project implements a mobile phone based traffic sign detection and recognition system assisted by a Convolutional Neural Network (CNN). Adequate effort is made to utilize heterogeneous computing as much as possible through the newly released *Android Neural Networks API* which is capable of intelligently distributing computationally intensive Neural Networks (NN) tasks to different available onboard accelerators such as GPUs, DSPs and FPGAs.


## 1.0 INTRODUCTION

As we know, Computer Vision has risen beyond just a mere field of study and research area
to a critical aspect of lives all around the planet. Today, our cars see (for themselves),
speed monitoring cameras in developing countries are able to efficiently identify erring
road users. Computer Vision applications go into even wider areas than we normally would
have imagined. Today, plants/recipes for beverage manufacturing can now be properly
classified and dealt with by line machines in factories without human intervention, however,
it is important to note that all of these capabilities have been made achievable due to
advances in the field of Computer Vision.

Just like any other field in science, these advances have shown us other areas of deficiency
in the normal day to day computing infrastructure we have in both our data centres and
mobile devices. It has successfully surged prime interests in areas of parallel and
heterogeneous computing, all in a bid to make room for the perfect integration of machine
learning applications in most devices.

This project implements a mobile phone-based traffic sign detection and recognition system
assisted by a Convolutional Neural Network (CNN). Adequate effort is made to utilize
heterogeneous computing as much as possible through the newly released Android Neural
Networks API which intelligently distributes computationally intensive Neural Networks (NN)
tasks to any available onboard accelerator (GPU/NN Accelerator). [The Neural Network portion of the project can be found here](https://github.com/OluwoleOyetoke/Computer_Vision_Using_TensorFlowLite)

The CNN is first trained with the German Traffic Sign Recognition Benchmark (GTSRB) training
database using Tensor Flow. The trained model is then embedded within the mobile device
alongside detection algorithms for traffic sign detection and classification.
Computationally expensive detection algorithms are re-written using OpenCL and directed to
the GPU/NN accelerator for dedicated processing.


## 2.0 AIM
Implement a fast-mobile vision app for traffic sign detection and classification


## 3.0 PROJECT DEFINITION
Essentially, the implementation of this project involves the building of a very easy to navigate Android App which interacts with captured video frames from a mobile device’s camera and performs Neural Networks classification operation. The apps interactivity with the trained CNN model is made possibel through TensorFlowLite and the Android NN API. In the near future, some critical  CNN use cases and detection functions will be implemented in OpenCL.

## 4.0 SOFTWARE DESIGN
The sections below provide more details on the Apps GUI and operation flow
### 4.1 GUI
![GUI1](https://github.com/OluwoleOyetoke/Accelerated-Android-Vision/blob/master/app/sampledata/GUI1.JPG)
![GUI2](https://github.com/OluwoleOyetoke/Accelerated-Android-Vision/blob/master/app/sampledata/GUI2.JPG)

### 4.2 APP FLOW / COMPONENT ANALYSIS
1. Button - Start: Takes user to the NN view.
2. Options – About: Takes user to the About View
3. Options – Collaborate: Takes user through the device’s web browser to project code
base on GitHub
4. Button – Collaborate: Takes user through the device’s web browser to project code
base on GitHub
5. Link – Download Trained Network: Download trained network to a local repository on
user’s mobile device. Uses ‘Toast’ to notify user of progress.
6. Button – Start NN Operation: Starts passing captured video frame through CNN for
detection and classification
