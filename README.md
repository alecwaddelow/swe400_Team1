-----------------------------------------
| Project 2b                            |
| Authors:    Drew Rife & Alec Waddelow |
| Professor:  Dr Wellington             |
| Class:      Large Scale Architectures |
-----------------------------------------

OVERVIEW:
Project 2b consists of being able to add and update an InventoryItem in the database.  

SOURCE FILES:

      src/data_source                                 src/domain
------------------------------               ------------------------------
CreateDatabase.java                          DBMapper.java 
CreateLinkTable.java                         Fastener.java
DatabaseGateway.java                         InventoryItem.java
LinkTableGateway.java                        LinkTableMapper.java 
                                             LoadInterface.java 
                                             Nail.java 
                                             NailMapper.java 
                                             PowerTool.java 
                                             PowerToolMapper.java 
                                             StripNail.java 
                                             StripNailMapper.java 
                                             Tool.java 
                                             ToolMapper.java 

         src/runner                                  src/userInput
------------------------------               ------------------------------
Runner.java                                  NailInput.java 
                                             PowerToolInput.java 
                                             StripNailInput.java 
                                             ToolInput.java 
                                             UserInput.java 

    test/{default package}                         test/data_source
------------------------------               ------------------------------
RunAllTests.java                              TestCloseConnection.java 
                                             TestCreateDatabase.java 
                                             TestCreateLinkTable.java 
                                             TestDatabaseGateway.java 
                                             TestLinkTableGateway.java 

        test/domain                                   test/enums 
------------------------------               ------------------------------
TestDBMapper.java                            InsertEnumData.java 
TestLinkTableMapper.java                     Nails.java 
TestNail.java                                PowerTools.java 
TestNailMapper.java                          StripNails.java 
TestPowerTool.java                           Tools.java 
TestPowerToolMapper.java 
TestStripNail.java 
TestStripNailMapper.java 
TestTool.java 
TestToolMapper.java 

        test/other                              test/SimulatedUserInput
------------------------------               ------------------------------
DBTest.java                                  TestSimulatedInput.java 
TestArrayList.java 
testRunner.java 





PROGRAM EXECUTION:

To run the program, the user must open the 'src' folder and open the 'runner' package. The user then should right click on Runner.java : Run As > Java Application 


TESTING EXECUTION:

To run the JUNIT test the user must open 'test' folder and open the 'default' package. The user then should right clock on RunAllTests.java : Run As > JUnit 

TEST COVERAGE EXECUTION:

To see the test coverage of the JUnit Tests the user must right click on RunAllTests.java : Coverage As > JUnit Test 