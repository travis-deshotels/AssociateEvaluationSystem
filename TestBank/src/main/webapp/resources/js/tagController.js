"use strict";

var url = "http://localhost:8080/TestBank/";
var app;

(function() {
	
	app = angular.module("tagMod", [ "ui.bootstrap", "ui.bootstrap.tpls" ]);
	app.controller("tagCtrl", function($http) {
		this.tags =[];
		this.delTag = '';
		this.newTag = {
				tagId : 0,
				tagName : ''
		};
		
		this.getTags = () =>{
			$http.get(url + "tag").then(
				// success
				(response) => {
					this.tags = response.data;
				},
				// failure
				() => {
					alert("Unable to retrieve tags!");
				}
			); 	
		};
		
		this.saveTag = () =>{
			if(this.newTag.tagName == ''){
				alert("Please enter a name for the Tag");
			}
			else{
				$http.post(url + "tag", this.newTag).then(
					// success
					(response) => {
						// add new tag to the list
						this.tags.push({
							tagId : this.newTag.tagId,
							tagName: this.newTag.tagName});
						// clear the textbox
						this.newTag = {
							tagId : 0,
							tagName : ''
						};
					},
					// failure
					() => {
						alert("Unable to save tag!");
					}
				);
			}
		};
		
		this.findTagId = () =>{
			for(var i=0;i<this.tags.length;i++){
				if(this.tags[i].tagName==this.delTag){
					return this.tags[i].tagId;
				}
			}
			return 0;
		};
		
		this.findIndexOfTag = () =>{
			for(var i=0;i<this.tags.length;i++){
				if(this.tags[i].tagName==this.delTag){
					return i;
				}
			}			
		};
		
		this.deleteTag = () =>{
			var delTagId = this.findTagId(); 
			var delTagName = this.delTag;
			if(delTagId==0){
				alert("Unable to remove tag!");
				return;
			}
			$http.delete(url + "tag/" + delTagId).then(
				// success
				() => {
					this.tags.splice(this.findIndexOfTag(), 1);
					this.delTag = '';
				},
				// failure
				() => {
					alert("Unable to remove tag!")
				}
			);
		};
		
		this.getTags();
	});
	
})();