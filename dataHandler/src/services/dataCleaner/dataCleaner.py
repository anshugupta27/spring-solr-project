
import email
import logging
import os
import pandas as pd

# Basic Configurations of Logger file where logs will be saved
logging.basicConfig(filename='logs.txt', filemode='a', level=logging.INFO)


class DataCleaner:

    # @Input : Takes Field Name and list of Data
    # Traverse whole input data and extract field header from it
    # Output : Returns list of the input Field extracted from data
    def get_field(self, field, messages):
        try:
            column = []
            for message in messages:
                e = email.message_from_string(message)
                column.append(e.get(field))
            logging.info('Emails Fields extraction completed Successfully! -' + field)
            return column

        except Exception as e:  # pragma: no cover
            print('ERROR:', e)

    # @Input : Takes the list of data to be processed
    # Traverse whole input data and extract body from it
    # Output : Return list of body extracted from input data
    def get_body(self, messages):
        try:
            column = []
            for message in messages:
                e = email.message_from_string(message)
                column.append(e.get_payload())
            logging.debug('Emails Body extraction completed Successfully')
            return column

        except Exception as e:  # pragma: no cover
            print('ERROR:', e)

    # This function Reads the data csv of Uncleaned data and
    # Returns a csv of cleaned data into cleaned file folder given in configurations.
    def clean_data(self, dataHandler):
        try:
            from ..configHelper.readWriteHelper import ReadWriteHelper
            readWriteHelper = ReadWriteHelper()
            file_path = readWriteHelper.getFullPath(dataHandler.configurations['uncleaned_data_path'])
            df = pd.read_csv(file_path)
            logging.debug('Un-Cleaned Data CSV read Successfully!')
            df['messageId'] = self.get_field("Message-ID", df['message'])
            df['date'] = self.get_field("Date", df['message'])
            df['subject'] = self.get_field("Subject", df['message'])
            df['from'] = self.get_field("X-From", df['message'])
            df['to'] = self.get_field("X-To", df['message'])
            df['cc'] = self.get_field("X-cc", df['message'])
            df['bcc'] = self.get_field("X-bcc", df['message'])
            df['mimeVersion'] = self.get_field("Mime-Version", df['message'])
            df['contentType'] = self.get_field("Content-Type", df['message'])
            df['text'] = self.get_body(df['message'])
            cols_to_drop = ['file', 'message']
            df.drop(cols_to_drop, axis=1, inplace=True)
            # Saving the csv to the indexed folder
            cleaned_file_path = os.path.join(dataHandler.configurations['cleaned_data_path'], 'cleaned_file.csv')
            df.to_csv(cleaned_file_path, index=False)
            logging.info('Cleaned Data CSV added Successfully!')

        except KeyError:  # pragma: no cover
            print('ERROR: Missing key:values in your config json file of data folders ')

        except ValueError:  # pragma: no cover
            print("ERROR: Please check weather data csv is present at given location and is not empty.")

        except Exception as e:  # pragma: no cover
            print("ERROR:", e)
