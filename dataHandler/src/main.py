import argparse
import logging

class DataHandler:
    
    # Class Constructor to initialized parsed input and process them
    def __init__(self, configPath, cleaningFlag="True", indexingFlag="True"):
        from services.configHelper.readWriteHelper import ReadWriteHelper
        # Basic Configurations of Logger file where logs will be saved
        LOG_FORMAT = '%(levelname)s %(asctime)s - %(message)s'
        loggerFilePath = "DataHandlerLogger.log"
        logging.basicConfig(filename=loggerFilePath,
                            level=logging.INFO,
                            format=LOG_FORMAT,
                            filemode='w')
        self.logger = logging.getLogger()
        self.configFilePath = configPath
        self.dataCleaningFlag = cleaningFlag
        self.dataIndexingFlag = indexingFlag
        self.readWriteHelper = ReadWriteHelper()
        try:
            self.configurations = self.readWriteHelper.loadConfiguration(self.configFilePath)
            self.logger.info("Config path : %s , Cleansed_Flag : %s, Index_Flag : %s extracted successfully",
                             self.configFilePath, self.dataCleaningFlag, self.dataIndexingFlag)
        except Exception as e:
            print("Error Occurred :", e)
        finally:
            self.logger.info('Datahandler constructor running finished')

    # Main function to run the desired condition as per user's input
    def run(self):  # pragma: no cover
        try:
            if self.dataCleaningFlag.lower() == "true":
                from services.dataCleaner.dataCleaner import DataCleaner
                dataCleaner = DataCleaner()
                dataCleaner.clean_data(self)
                print('cleaning done')
            if self.dataIndexingFlag.lower() == "true":
                from services.dataUploader.dataUploader import DataUploader
                dataUploader = DataUploader()
                dataUploader.index_data(self)
                print('indexing done')

        except Exception as e:  # pragma: no cover
            print('ERROR:', e)
            self.logger.info('ERROR:', e)


if __name__ == '__main__':  # pragma: no cover
    parser = argparse.ArgumentParser()
    parser.add_argument('-c', help='Config File Path for Data Handler', dest='configFilePath', required=True)
    parser.add_argument('-cf', help='Cleaned File Flag for DSIU_Processor', dest='dataCleaningFlag', default="True")
    parser.add_argument('-if', help='Index File Flag for DSIU_Processor', dest='dataIndexingFlag', default="True")
    arguments = parser.parse_args()
    configFilePath = arguments.configFilePath
    dataCleaningFlag = arguments.dataCleaningFlag
    dataIndexingFlag = arguments.dataIndexingFlag
    dataHandler = DataHandler(configFilePath, dataCleaningFlag, dataIndexingFlag)
    dataHandler.run()
