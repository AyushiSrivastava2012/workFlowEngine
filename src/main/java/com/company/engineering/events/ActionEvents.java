package com.company.engineering.events;

public enum ActionEvents{
	
	FetchDocumentFromMongo{
		public boolean execute(){
			return true;
		};
	},
	InsertUserInMongo {
		public boolean execute(){
			return true;
		};
	}
	,
	FireL1ApprovalEvent{
		public boolean execute(){
			return true;
		};
	},
	//previous
	CreateUser{
		public boolean execute(){
			return true;
		};
	},
	L1ApprovalPending{
		public boolean execute(){
			return true;
		};
	},
	L2ApprovalPending{
		public boolean execute(){
			return true;
		};
	},
	L1ApprovalRejected{
		public boolean execute(){
			return true;
		};
	},
	L2ApprovalRejected{
		public boolean execute(){
			return true;
		};
	},
	AllApproved{
		public boolean execute(){
			return true;
		};
	},
	Executed{
		public boolean execute(){
			return true;
		};
	};
}