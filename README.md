# e-commuter Generator

Sample data generator to be used with e-commuter

## Usage

Import [e-commuter](https://github.com/lcappuccio/e-commuter) for the Postal Code Database classes as dependency for this project.

Compile, deploy and configure [e-commuter](https://github.com/lcappuccio/e-commuter).

### AddressGenerator

`org.systemexception.ecommuter.generator.main.AdressGenerator` will generate file `output.txt` with random addresses.
The steps are:
* read Milano street names from `milano_addresses.txt`
* parse each line and form an address string
* send the address string to e-commuter and generate a json representation of `org.systemexception.ecommuter.model.Address`

At this point you should invoke e-commuter's `addressToGeo` endpoint to obtain `milano_ecommuter_addresses.txt` but I'm
 sparing you this step :) (see: `org.systemexception.ecommuter.generator.pojo.HttpConnector.getAddressObjectAsStringFor`)

### ConvertObjectAndPost
 
Will read all generated addresses and two lists with names and surnames and create a `org.systemexception.ecommuter.model.Person` object.
After creating the object will send it to e-commuter's endpoint `person/add`. (see: `org.systemexception.ecommuter.generator.pojo.HttpConnector.postPerson`)

* configure `org.systemexception.ecommuter.generator.pojo.HttpConnector.postPerson` IP address and port.
