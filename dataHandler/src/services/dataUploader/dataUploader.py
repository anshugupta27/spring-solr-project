# Importing Required Libraries
import pysolr
import csv
import sys
import logging

# Basic Configurations of Logger file where logs will be saved
logging.basicConfig(filename='logs.txt', filemode='a', level=logging.INFO)


# Class to connect to Solr connection and Index the Cleaned data into it.
class DataUploader:  # pragma: no cover

    # @Input : Take solr connection string and collection name
    # Connect with Solr Cloud Collection using Zookeeper
    # @Output : Return the solr connection
    @staticmethod
    def create_solr_connection(solr_zookeeper_string, solr_collection_name):  # pragma: no cover
        zookeeper = pysolr.ZooKeeper(solr_zookeeper_string)
        solr = pysolr.SolrCloud(zookeeper, solr_collection_name)
        logging.info('Solr Connection Created Successfully!')
        return solr

    # @Input : Take solr connection object and dataset to index
    # Add the data from dataset int solr in chunks of 10,000 entries.
    # @Output : Data Index and ready to be used.
    @staticmethod
    def push_data_into_solr(dataHandler, solrObject, datasetPath):  # pragma: no cover
        maxInt = sys.maxsize
        while True:
            try:
                csv.field_size_limit(maxInt)
                break
            except OverflowError:
                maxInt = int(maxInt / 10)

        csvFile = open(datasetPath, 'r')
        fieldnames = tuple(dataHandler.configurations['field_names'])
        reader = csv.DictReader(csvFile, fieldnames)
        CHUNK_SIZE = dataHandler.configurations['chunk_size_for_solr_upload']
        data_chunk = []
        counter = 0
        for row in reader:
            data_chunk.append(row)
            counter += 1
            if counter == CHUNK_SIZE:
                solrObject.add(data_chunk)
                logging.info('Data Added in Chunk of 10000 !')
                counter = 0
                data_chunk = []
        if len(data_chunk) < CHUNK_SIZE:
            solrObject.add(data_chunk)
        logging.info('Data Added Successfully !')

    # This function fetch the cleaned file from cleaned file folder and index it at Solr
    # Cloud of given configuration
    def index_data(self, dataHandler):  # pragma: no cover
        try:
            from ..configHelper.readWriteHelper import ReadWriteHelper
            readWriteHelper = ReadWriteHelper()
            solrHandler = self.create_solr_connection(dataHandler.configurations['solr_zookeeper_string'],
                                                      dataHandler.configurations['solr_collection_name'])
            logging.info('Solr Cloud Connection Created Successfully!')
            cleaned_file_path = readWriteHelper.getFullPath(dataHandler.configurations['cleaned_data_path'])
            self.push_data_into_solr(dataHandler, solrHandler, cleaned_file_path)
            logging.info("Cleaned Data Indexed Successfully!")

        except Exception as e:
            print('ERROR:', e)
