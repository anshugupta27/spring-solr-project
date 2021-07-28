import argparse
import logging
from services.configHelper.readWriteHelper import ReadWriteHelper

# Basic Configurations of Logger file where logs will be saved
logging.basicConfig(filename='mainLogs.txt', filemode='a', level=logging.INFO)


class DataHandler:  # pragma: no cover   <--
    configFilePath = ''
    configurations = None
    dataCleaningFlag: bool
    dataIndexingFlag: bool

    # Class Constructor to initialized parsed input and process them
    def __init__(self, configPath, cleaningFlag="True", indexingFlag="True"):
        self.configFilePath = configPath
        self.dataCleaningFlag = cleaningFlag
        self.dataIndexingFlag = indexingFlag
        readWriteHelper = ReadWriteHelper()
        self.configurations = readWriteHelper.loadConfiguration(self.configFilePath)
        logging.debug('Input file types and path extracted!')

    # Main function to run the desired condition as per user's input
    def run(self):
        try:
            from services.dataUploader.dataUploader import DataUploader
            from services.dataCleaner.dataCleaner import DataCleaner
            if self.dataCleaningFlag == True:
                dataCleaner = DataCleaner()
                dataCleaner.clean_data(self)
            if self.dataIndexingFlag == True:
                dataUploader = DataUploader()
                dataUploader.index_data(self)

        except Exception as e:  # pragma: no cover
            print('ERROR:', e)


if __name__ == '__main__':  # pragma: no cover   <--
    parser = argparse.ArgumentParser()
    parser.add_argument('-c', help='Config File Path for Data Handler', dest='configFilePath', required=True)
    parser.add_argument('-cf', help='Cleaned File Flag for DSIU_Processor', dest='dataCleaningFlag', default=True)
    parser.add_argument('-if', help='Index File Flag for DSIU_Processor', dest='dataIndexingFlag', default=True)
    arguments = parser.parse_args()
    configFilePath = arguments.configFilePath
    dataCleaningFlag = arguments.dataCleaningFlag
    dataIndexingFlag = arguments.dataIndexingFlag
    dataHandler = DataHandler(configFilePath, dataCleaningFlag, dataIndexingFlag)
    dataHandler.run()
