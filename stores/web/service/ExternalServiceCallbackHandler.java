
/**
 * ExternalServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 19, 2008 (10:13:39 LKT)
 */

    package com.oim.stores.web.service;

    /**
     *  ExternalServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class ExternalServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public ExternalServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public ExternalServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for queryUsefulNumber method
            * override this method for handling normal response from queryUsefulNumber operation
            */
           public void receiveResultqueryUsefulNumber(
        		   com.oim.stores.web.service.ExternalServiceStub.QueryUsefulNumberResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from queryUsefulNumber operation
           */
            public void receiveErrorqueryUsefulNumber(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for createOrder method
            * override this method for handling normal response from createOrder operation
            */
           public void receiveResultcreateOrder(
        		   com.oim.stores.web.service.ExternalServiceStub.CreateOrderResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from createOrder operation
           */
            public void receiveErrorcreateOrder(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for searchOrder method
            * override this method for handling normal response from searchOrder operation
            */
           public void receiveResultsearchOrder(
        		   com.oim.stores.web.service.ExternalServiceStub.SearchOrderResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from searchOrder operation
           */
            public void receiveErrorsearchOrder(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for occupyReserved method
            * override this method for handling normal response from occupyReserved operation
            */
           public void receiveResultoccupyReserved(
        		   com.oim.stores.web.service.ExternalServiceStub.OccupyReservedResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from occupyReserved operation
           */
            public void receiveErroroccupyReserved(java.lang.Exception e) {
            }
                


    }
    