import logging
import json
from json import JSONDecodeError
import os

# Basic Configurations of Logger file where logs will be saved
logging.basicConfig(filename='mainLogs.txt', filemode='a', level=logging.INFO)


class ReadWriteHelper:

    # @Input : Path to Configuration File Path
    # Extract config file from given path and process it
    # @Output : Return JSON file of Configurations
    def loadConfiguration(self, configPath):
        try:
            with open(configPath) as file:
                configurations = json.load(file)
                logging.info('Configuration File parsed and converted into JSON')
                return configurations

        except PermissionError:   # pragma: no cover
            print("ERROR: Please provide correct path to Your config.json file")

        except JSONDecodeError:   # pragma: no cover
            print("ERROR: Please provide necessary Information in your config.json file ")

        except Exception as otherErrors:   # pragma: no cover
            print(otherErrors)

        finally:
            file.close()

    # @Input : Takes the path of folder
    # Search the file presents into that folder
    # @Output : Return the path of file present in the input folder
    def getFullPath(self, folder_path):
        try:
            filename = next(os.walk(folder_path), (None, None, []))[2][0]
            file_path = os.path.join(folder_path, filename)
            logging.debug('CSV files location fetched Successfully!')
            return file_path

        except KeyError:   # pragma: no cover
            print("ERROR: Missing key:values in your config json file of data folders")

        except ValueError:   # pragma: no cover
            print("ERROR: Please provide correct path of folder in your config json file")

        except IndexError:   # pragma: no cover
            print("ERROR: Data Not Found !!! ")

        except Exception as e:   # pragma: no cover
            print("ERROR:", e)
