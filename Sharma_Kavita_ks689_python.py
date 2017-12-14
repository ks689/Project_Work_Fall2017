#=======================================================================================================
# GUI to read and process data from a file
#=======================================================================================================
# Import Packacges
import numpy as np
import re
import os
import tkinter
from tkinter import *
from tkinter.filedialog import *
root = tkinter.Tk()

# Script Location
script_dir = os.path.dirname(os.path.abspath(__file__))

#-----------------------------------------------------------------------------------------
# input file
fileLabel = Label(root, text = 'Select a File for Scaling \n (.obj or .obj.txt)').grid(row = 0, column = 2, padx = 5, pady = 5)
inputFileName = StringVar()
inputFileEntry = Entry(root, textvariable = inputFileName)
inputFileEntry.grid(row = 0, column = 0, padx = 5, pady = 5)
    
# event handler   
def browseInput():
    inputFileName.set(askopenfilename(title="Select input file", initialdir=script_dir ))

# browse input button
browseInputButton = Button(root, fg ="blue" , text = "Browse", command = browseInput).grid(row = 0, column = 1, padx = 5, pady = 5)

#-----------------------------------------------------------------------------------------
# output file
fileLabel = Label(root, text = 'Enter name for output file with ext\n (.obj or .obj.txt)').grid(row = 1, column = 2, padx = 5, pady = 5)
outputFileName = StringVar()
outputFileEntry = Entry(root, textvariable = outputFileName)
outputFileEntry.grid(row = 1, column = 0, padx = 5, pady = 5)

# event handler   
def browseOutput():
    outputFileName.set(asksaveasfilename(title="Select output file", initialdir=script_dir ))

# browse input button
browseOutputButton = Button(root, fg ="blue" , text = "Browse", command = browseOutput).grid(row = 1, column = 1, padx = 5, pady = 5)

# Ask User for Factors
fileLabel = Label(root, text = 'Enter a scaling Factor').grid(row = 2, column = 1, padx = 5, pady = 5)
factorEntryInt = DoubleVar()
factorEntry = Entry(root, textvariable = factorEntryInt)
factorEntry.grid(row = 2, column = 0, padx = 5, pady = 5)



#-----------------------------------------------------------------------------------------
# Calculation
def calcResult():
    f_in = open(inputFileName.get(), "r")
    f_out = open(outputFileName.get(), "w")
    factor=factorEntryInt.get()


    f_list=[]
    x=0
    #Running the loop and checking the file line by line
    for s in f_in.readlines():
        
        if x <=4:
            x+=1
        if 'v' in s:
            
            #function for scaling eaxh number from the text file beginning with v
            def repf(matchobj):
                i =float(matchobj.group())
                if factor == 0:
                    return str(i) #scaling not done if input is 0
                else:
                    return str(i * factor) #Scaling by user input attribute
                
            
            result = re.sub(r'[-+]?\d*\.\d+|\d+', repf, s)
            f_list.append(result)
            
        if 'f' in s:
            f_list.append(s)
    
    # Output to the user specified file.
    for i in f_list:
        f_out.write(i) 

    f_in.close()
    f_out.close()

calcButton = Button(root, fg ="blue" , text = "Calculate", command = calcResult).grid(row = 3, column = 1, padx = 5, pady = 5)


root.title('SELECT FILE')
root.mainloop()

