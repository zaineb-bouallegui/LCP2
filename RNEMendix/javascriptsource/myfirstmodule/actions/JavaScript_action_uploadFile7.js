// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
import "mx-global";
import { Big } from "big.js";

// BEGIN EXTRA CODE
// END EXTRA CODE

/**
 * @param {MxObject} doc7
 * @returns {Promise.<MxObject>}
 */
export async function JavaScript_action_uploadFile7(doc7) {
	// BEGIN USER CODE
    return new Promise((resolve, reject) => {
        let fileInput = document.getElementById('myfile7');
        fileInput.style.display = 'block'; // Override display:none to display:block

        // Add change event listener to file input
        fileInput.addEventListener('change', function () {
            let files = fileInput;
            let fr = new FileReader();
            fr.onload = function () {
              //  console.warn(fr.result);
               // console.warn(files[0].name); // Log the file name

                let content = fr.result;
                let base64Content = btoa(content); // Encode as base64 string
                let fileName = files.files[0].name;
                //console.warn(base64Content);
                let fileType = files.files[0].type;
               // console.warn(fileName);
               // console.warn(fileType);
                //console.warn(base64Content);

                doc7.set("nom", fileName);
                doc7.set("_type", fileType);
                doc7.set("contenu", base64Content);

                resolve(doc7);
            }
            fr.readAsBinaryString(this.files[0]);
        });

        // Open file chooser window automatically
        fileInput.click();
    });
	// END USER CODE
}
