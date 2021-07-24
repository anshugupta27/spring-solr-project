import sys
sys.path.insert(0, '../src')
sys.path.insert(0, '../src/services')
sys.path.append('C:/Users/Administrator/Documents/Maven/dsiu/dataHandler/src')

import filecmp
import unittest
from src.main import DataHandler
from src.services.dataCleaner.dataCleaner import DataCleaner
from src.services.configHelper.readWriteHelper import ReadWriteHelper


class DataHandlerTester(unittest.TestCase):
    configurations = None
    configFilePath = "C:/Users/Administrator/Documents/Maven/dsiu/dataHandler/tests/conf/config.json"
    cleaningFlag = True
    indexingFlag = False

    def setUp(self):
        self.dataHandler = DataHandler(self.configFilePath, self.cleaningFlag, self.indexingFlag)

    def test__00_checkDataCleaner(self):
        self.configurations = self.dataHandler.configurations
        dataCleaner = DataCleaner()
        readWriteHelper = ReadWriteHelper()
        dataCleaner.clean_data(self.dataHandler)
        expectedFilePath = readWriteHelper.getFullPath(self.configurations['expected_data_path'])
        processedFilePath = readWriteHelper.getFullPath(self.configurations['cleaned_data_path'])
        self.assertTrue(filecmp.cmp(processedFilePath, expectedFilePath, shallow=False), 'Test Failed!!')


if __name__ == '__main__':
    runner = unittest.TextTestRunner
    unittest.main()
