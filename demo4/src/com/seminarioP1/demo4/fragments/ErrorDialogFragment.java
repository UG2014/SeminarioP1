package com.seminarioP1.demo4.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class ErrorDialogFragment extends DialogFragment {

		private Dialog dialog;
		public ErrorDialogFragment(){
			super();
			dialog= null;
		}
		
		public void setDialog(Dialog dialog){
			this.dialog = dialog;
		}
		
		public Dialog onCreateDialog(Bundle savedInstace){
			return dialog;
		}
		
}
