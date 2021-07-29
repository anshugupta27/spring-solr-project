import logging
import json
from json import JSONDecodeError
import os

# Basic Configurations of Logger file where logs will be saved
logging.basicConfig(filename='logs.txt', filemode='a', level=logging.INFO)

class ReadWriteHelper:

    # @Input : Path to Configuration File Path
    # Extract config file from given path and process it
    # @Output : Return JSON file of Configurations
    @staticmethod
    def loadConfiguration(configPath):
        try:
            configurations = {}
            with open(configPath) as file:
                configurations = json.load(file)
                logging.info('Configuration File parsed and converted into JSON')

        except PermissionError:  # pragma: no cover
            print("ERROR: Please provide correct path to Your config.json file")

        except JSONDecodeError:  # pragma: no cover
            print("ERROR: Please provide necessary Information in your config.json file ")

        except Exception as otherErrors:  # pragma: no cover
            print(otherErrors)

        finally:
            file.close()
            return configurations

    @staticmethod
    def getCleanedFilePath(cleaned_data_path):
        try:
            file_path = ""
            file_path = os.path.join(cleaned_data_path, 'cleaned_file.csv')
            logging.info('Clean file path created Successfully!')

        except KeyError:  # pragma: no cover
            print("ERROR: Missing key:values in your config json file of data folders")

        except ValueError:  # pragma: no cover
            print("ERROR: Please provide correct path of folder in your config json file")

        except IndexError:  # pragma: no cover
            print("ERROR: Data Not Found !!! ")

        except Exception as e:  # pragma: no cover
            print("ERROR:", e)

        finally:
            return file_path

    # @Input : Takes the path of folder
    # Search the file presents into that folder
    # @Output : Return the path of file present in the input folder
    @staticmethod
    def getFullPath(folder_path):  # pragma: no cover
        try:
            file_path = ""
            filename = next(os.walk(folder_path), (None, None, []))[2][0]
            file_path = os.path.join(folder_path, filename)
            logging.debug('CSV files location fetched Successfully!')

        except KeyError:  # pragma: no cover
            print("ERROR: Missing key:values in your config json file of data folders")

        except ValueError:  # pragma: no cover
            print("ERROR: Please provide correct path of folder in your config json file")

        except IndexError:  # pragma: no cover
            print("ERROR: Data Not Found !!! ")

        except Exception as e:  # pragma: no cover
            print("ERROR:", e)

        finally:
            return file_path