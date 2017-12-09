
global class DeliverySharingRecalculation implements Database.Batchable<sObject> {
    
    global Database.QueryLocator start(Database.BatchableContext BC){
        return Database.getQueryLocator([SELECT Id, Name FROM ToDo__c]);  
    }
    
    global void execute(Database.BatchableContext BC, List<sObject> scope){
        // In this case i am just simply shating the data with simply one user . But you can adjest the data based on different conditions . Examples If Delivery moode is express you can assign to specific gruop id Delivery
        // is normal you can assign to some other group 
        List<ToDo__Share> newToDo = new List<ToDo__Share>();
        List<ToDo__c> todo =(List<ToDo__c>)scope ; 
        
        for(ToDo__c de : todo){
            ToDo__c delSH = new ToDo__c();
            // Assign in to user or group 
            delSH.UserOrGroupId = '00541000000yyyy';
            //Access level 
            delSH.AccessLevel = 'Read';
            // Record Id
            delSH.ParentId = de.Id;
            // Row Cause 
            delSH.RowCause = Schema.ToDo__Share.RowCause.Custom_Sharing_Model__c;
            newToDo.add(delSH);
            // You can add new sharing and delete the existing sharing based on requirementts 
        }
        Database.SaveResult[] lsr = Database.insert(newToDo,false);
        
    }
    
    global void finish(Database.BatchableContext BC){  
    }
    
}