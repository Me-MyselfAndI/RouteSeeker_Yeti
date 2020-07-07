# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'Menu.ui'
#
# Created by: PyQt5 UI code generator 5.13.2
#
# WARNING! All changes made in this file will be lost!


from PyQt5 import QtCore, QtGui, QtWidgets




teamNumber = 0
path = []
file = 0


class Link (object):
    num = 0
    foll = 0
    pos = [0, 0]
    action = 0
    isLast = False
    
    def __init__ (self, num, foll, pos, action = 0, isLast = False):
        self.num = num
        self.foll = foll
        self.pos = pos
        self.action = action
        self.isLast = isLast
    



class Ui_Dialog(object):
    
    pixelCount = [63, 46] 
    
    gridHeight = 1030
    gridWidth = 824
    
    gridPosition = [10, 10]
    
    pixels = {}       
    
    def sendClicked (self):
        teamNumber = self.teamNumberLine.text()
        speed = self.speedLine.text()
        shootingTime = self.shootingTimeLine.text()
        
        
        self.teamNumberLine.setText("")
        self.speedLine.setText("")
        self.shootingTimeLine.setText("")
        
        print (teamNumber)
        (i, j) = (0, 0)
        print (self.pixels[(i, j)].text())
#--------------------------------------------------
        first = Link (128, 0, [-1, -1], 0, True)
        first.foll = first
        for i in range (self.pixelCount[0]):
            for j in range (self.pixelCount[1]):
                print ("reviewing [" + str(i) + "][" + str(j) + "]")
                if (self.pixels[(i, j)].text() == ""):
                    print ("\tempty")
                    continue
                string = self.pixels[(i, j)].text().lower() + 'e'
                for symb in range (len(string)):
                    if ((ord(string[symb]) > 47) and (ord(string[symb]) < 58)):
                        print ("\tThis is a number: " + string[symb])
                        action = string[symb+1] if (string[symb+1] == 'l' or string[symb+1] == 's') else ""
                        print ("\tAction: " + action)
                        if (ord(string[symb]) - 48 < first.num):
                            print ("\tSmaller than the old first: " + str(first.num))
                            first = Link (ord(string[symb]) - 48, first, (i,j), action)
                        #-------------------------------------------------------------------------
                            print ("\tIs:\n")
                            kinda_current = first
                            while (kinda_current.isLast == False):
                                print ("\t\t" + str(kinda_current.pos) + " " + (kinda_current.action if kinda_current.action != 0 else "") + "Next is ---" + str(kinda_current.foll.num))
                                kinda_current = kinda_current.foll
                        #--------------------------------------------------------------------------
                            continue
                        print ("\tGreater than the first that equals: " + str(first.num))
                        current = first
                        wasLast = first.isLast
                        print ("\tGoing through the chain")
                        print ("\t\tCurrent node: " + str(current.num) + "; Last? - " + str(wasLast))
                        while ((current.foll.isLast == False) and (ord(string[symb]) -48 >= current.foll.num)):
                            wasLast = current.isLast
                            current = current.foll
                            print ("\t\tCurrent node: " + str(current.num) + "; Last? - " + str(wasLast))                    
                        print ("\tSearch is over. Previous node: " + str(current.num))
                        newLink = Link (ord(string[symb]) - 48, current.foll, (i, j), action, wasLast)
                        current.foll = newLink
                #-------------------------------------------------------------------------
                        print ("\tIs:\n")
                        kinda_current = first
                        while (kinda_current.isLast == False):
                            print ("\t\t" + str(kinda_current.pos) + " " + (kinda_current.action if kinda_current.action != 0 else "") + "Next is ---" + str(kinda_current.foll.num))
                            kinda_current = kinda_current.foll
                    #--------------------------------------------------------------------------

        teamInfo = "(" + teamNumber + ", " + speed + ", " + shootingTime + "), "

        path = [teamInfo]                            
        current = first
        file = open('teamTrajectories.txt', 'a')
        file.write("\n" + teamInfo)
        while (current.isLast == False):
            print ("Appending: " + str(current.pos))
            appendix = str(current.pos) + (current.action if current.action != "" else "")
            path.append (appendix)
            file.write (appendix + ", ")
            current = current.foll
        
        file.write("e")
        file.close()
        print (path)
#--------------------------------------------------
                        
    
    
    def setupUi(self, Dialog):
        Dialog.setObjectName("Dialog")
        Dialog.resize(1200, 1070)
        
        self.teamBoxWidget = QtWidgets.QWidget(Dialog)
        self.teamBoxWidget.setGeometry(QtCore.QRect(self.gridPosition[0] + self.gridWidth + 20, 400, 130, 120))
        self.teamBoxWidget.setObjectName("teamBoxWidget")
        self.teamBox = QtWidgets.QVBoxLayout(self.teamBoxWidget)
        self.teamBox.setContentsMargins(0, 0, 0, 0)
        self.teamBox.setObjectName("teamBox")
        
        self.teamNumberLine = QtWidgets.QLineEdit(self.teamBoxWidget)
        self.teamNumberLine.setMaximumSize(QtCore.QSize(16777215, 20))
        self.teamNumberLine.setText("")
        self.teamNumberLine.setAlignment(QtCore.Qt.AlignCenter)
        self.teamNumberLine.setObjectName("teamNumberLine")
        self.teamBox.addWidget(self.teamNumberLine)
        
        self.speedLine = QtWidgets.QLineEdit(self.teamBoxWidget)
        self.speedLine.setAlignment(QtCore.Qt.AlignCenter)
        self.speedLine.setObjectName("speedLine")
        self.teamBox.addWidget(self.speedLine)
    
        self.shootingTimeLine = QtWidgets.QLineEdit(self.teamBoxWidget)
        self.shootingTimeLine.setAlignment(QtCore.Qt.AlignCenter)
        self.shootingTimeLine.setObjectName("shootingTimeLine")
        self.teamBox.addWidget(self.shootingTimeLine)

        self.sendButton = QtWidgets.QPushButton(self.teamBoxWidget)
        self.sendButton.setMaximumSize(QtCore.QSize(16777215, 23))
        self.sendButton.setObjectName("sendButton")
        self.teamBox.addWidget(self.sendButton)   
        self.sendButton.clicked.connect(self.sendClicked)
        
        self.settButton = QtWidgets.QPushButton(Dialog)
        self.settButton.setGeometry(QtCore.QRect(1000, 525, 190, 510))
        self.settButton.setObjectName("settButton")
     
        self.viewButton = QtWidgets.QPushButton(Dialog)
        self.viewButton.setGeometry(QtCore.QRect(1000, 10, 190, 510))
        self.viewButton.setObjectName("viewButton")
        
        self.Field = QtWidgets.QLabel(Dialog)
        self.Field.setGeometry(QtCore.QRect(self.gridPosition[0], self.gridPosition[1], self.gridWidth, self.gridHeight))
        self.Field.setText("")
        self.Field.setPixmap(QtGui.QPixmap("Field1.png"))
        self.Field.setScaledContents(True)
        self.Field.setObjectName("Field")
        
        self.gridLayoutWidget = QtWidgets.QWidget(Dialog)
        self.gridLayoutWidget.setGeometry(QtCore.QRect(self.gridPosition[0], self.gridPosition[1], self.gridWidth, self.gridHeight))
        self.gridLayoutWidget.setObjectName("gridLayoutWidget")
        self.gridLayout = QtWidgets.QGridLayout(self.gridLayoutWidget)
        self.gridLayout.setContentsMargins(0, 0, 0, 0)
        self.gridLayout.setObjectName("gridLayout")




        for i in range(self.pixelCount[0]):
            for j in range(self.pixelCount[1]):
                # keep a reference to the buttons
                self.pixels[(i, j)] = QtWidgets.QLineEdit(self.gridLayoutWidget)
                # add to the layout
                self.gridLayout.addWidget(self.pixels[(i, j)], i, j)
                        
        self.retranslateUi(Dialog)
        QtCore.QMetaObject.connectSlotsByName(Dialog)
        
    def retranslateUi(self, Dialog):
        _translate = QtCore.QCoreApplication.translate
        Dialog.setWindowTitle(_translate("Dialog", "Dialog"))
        self.settButton.setText(_translate("Dialog", "Set teammates\' paths"))
        self.viewButton.setText(_translate("Dialog", "View map and trajectory"))
        self.teamNumberLine.setPlaceholderText(_translate("Dialog", "Enter Team Number"))
        self.speedLine.setPlaceholderText(_translate("Dialog", "Average Speed During Autonomous"))
        self.shootingTimeLine.setPlaceholderText(_translate("Dialog", "Shooting Time"))
        self.sendButton.setText(_translate("Dialog", "Ok"))


if __name__ == "__main__":
    import sys
    app = QtWidgets.QApplication(sys.argv)
    Dialog = QtWidgets.QDialog()
    ui = Ui_Dialog()
    ui.setupUi(Dialog)
    Dialog.show()

    sys.exit(app.exec_())

