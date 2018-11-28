//This helper method calls the HTML5 Constraint Validation API
//to validate the rules set on the form fields.
//If data in the form not valid, it prevents form from being submitted.
//After checking validity, it turns on the Bootstrap 
//validation message handling
//Note: It is important that the function passed in via the custValidationFunc inpupt
//parameter performs validation according to the HTML5 form constraint validation API: it should
//setCustomValidity on specific fields, instead of returning true/false to caller to
//indicate invalid fields.
//it is assumed that the custValidationFunc passed in takes a parameter that represents
//the <form> being validated
function validateForm(form, custValidationFunc) {
    
    if(custValidationFunc && typeof (custValidationFunc)=='function'){
        custValidationFunc(form);
    }
    
    var valid = form.checkValidity();
    //prevent form been submitted if form is not validate
    //Note: this is only needed if a form is being submitted
    //with a submit button (<button type="submit"...>)
    
    if (!valid) {
        event.preventDefault();
        event.stopPropagation();
    }
    
    //set the bootstrap class 'was-validated' to cause the
    //validation messages to toggle to visible
    form.classList.add('was-validated');

    return valid;
}

function submitFormPartial(srcElement, custValidationFunc, mixedResponse, jsonFn) {
    try {
           //if the srcElement is not explicitly passed in, let's
            //assign it to "this", which should represent
            //the html element that triggered this onclick call event in all browser types.
        
        var form = getFormFromControl(srcElement);

        var dataValid = validateForm(form, custValidationFunc);
        //TODO: add logic to call the custValidationFunc here;
        
        if (!dataValid)
        return false;

        var actionUrl = form.getAttribute('action')
        actionUrl = location.origin + actionUrl;
        var partialTarget = form.getAttribute('data-partial-target');
        var parent = form.getAttribute('data-parent-container');
        var modalId = srcElement.getAttribute('data-close-target');
        var formData = $(form).serialize();

        $.ajax( {
            type : "POST", url : actionUrl, data : formData, 
            
            success : function (data, statusText, xhr) {
                
                if (mixedResponse) {                   
                   for (fragId in data.fragments) {
                      $('#' + fragId).html(data.fragments[fragId]);
                   }
                   if (typeof (jsonFn) === 'function') {
                      jsonFn(data.objs);
                   }                  
                }
                else {
                //check if the server side controller has
                   //set a refreshTarget header variable value.
                   ////The valid values for this header variable are:
                   //1. 'parent-container', which means stay with the current section 
                   //likely due to server side rule/validation failure; In this case,
                   //the <form> that enclose this control submitting
                   //this request is expected to have an attribute named 
                   //'data-parent-container' set to the id of a valid element that contains 
                   //this <form>.
                   //2. 'partial-target', which mean the save is successful and refresh to
                   //the section the 'data-partial-target' attribute set on the <form>.
                   var refreshTarget = xhr.getResponseHeader('refreshTarget');
                   if(refreshTarget && refreshTarget == 'parent-container'){
                       partialTarget = parent;
                   }else{ //not staying on modal; so close it
                       if (modalId) {
                       //if 'data-close-target' is defined on the button clicked, this will be true
                       $('#' + modalId).modal('hide');
                       }
                   }
                   $('#' + partialTarget).html(data);
                }
            }
        });

    }
    catch (e) {
        //log it to console?            
    }
    //always return false so that the form is not submitted by the button as a full submit
    return false;
}


function closeModal(modalId){
    var modalSelector = '#' + modalId;
      $(modalSelector).modal('hide'); 
}

/*This is a helper method that gets the HTML form for the control (button, input)
 * passed in.
 * If the element is enclosed in <form></form> tags, then it will return the <form> that encloses
 * the control.
 * If the element is not enclosed in a <form></form> tags, then it is expected to have the
 * HTML5 form attribute on the element as illustrated below:
      <form id="saveForm" action="/post/dispatch/save" method="post">
            <input type="text" name="foo"/>
            ...
       </form>
         
        <form id="deleteForm" action="/post/dispatch/delete" method="post">
            ...
            <input type="hidden" value="some_id"/>
        </form>
        
        <div id="buttonBar">
            <input type="submit" name="save" value="Save" form="saveForm" onClick="submitFormPartial(this)"/>
            <input type="submit" name="delete" value="Delete" form="deleteForm" onClick="submitFormPartial(this)"/>
        </div>
        
    The above example is intended to help with situation where different buttons in the same block (e.g. same row next to each other)
    needs to submit different data to different server-side controller methods to be processed differently
    and have different client side refresh needs.
    
 */
function getFormFromControl(element){
    var form = element.form;
    if(!form){
        //IE11 or below does not support the HTML5 form attribute on input tags;
        //So if the input element (e.g. button) is outside <form></form> tags, 
        //the element.form will return null (or undefined).
        //In such cases, we will need to programmatically use the 'form' attribute 
        //expected on the element (e.g. button) to look up the form for the button. 
        //The value of the 'form' attribute must be the name/id of a valid form in the document; otherwise
        //this method will return a null;
        
        var frmAttr = element.getAttribute('form');
        if(frmAttr){
            form = document.getElementById(frmAttr);
        }
    }
    
    return form;
}

var MixedResponse = {YES : true, NO : false};