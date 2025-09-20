(function () {
        "use strict";
        var form = document.getElementById("myForm");

        form.addEventListener("submit", function (event) {
          if (!form.checkValidity()) {
            event.preventDefault();
            event.stopPropagation();
          }

          // Password validation
          let passwordInput = document.getElementById("password");
          let confirmPasswordInput = document.getElementById("confirmPassword");
          if (passwordInput.value && confirmPasswordInput.value !== passwordInput.value) {
            confirmPasswordInput.setCustomValidity("Passwords do not match.");
          } else {
            confirmPasswordInput.setCustomValidity("");
          }

          //Phone Validation
          let phoneInput = document.getElementById("phone");
          let phoneRegex = /^[0-9]{10}$/;
          if (!phoneRegex.test(phoneInput.value)) {
            phoneInput.setCustomValidity("Please enter a valid 10-digit phone number.");
          } else {
            phoneInput.setCustomValidity("");
          }

          //Pincode Validation
          let pincodeInput = document.getElementById("pincode");
          let pinRegex = /^\d{6}$/;
          if (!pinRegex.test(pincodeInput.value)) {
            pincodeInput.setCustomValidity("Please enter a valid pincode.");
          } else {
            pincodeInput.setCustomValidity("");
          }

          // Custom validation for start and end dates
          let startDateInput = document.getElementById("startDate");
          let endDateInput = document.getElementById("endDate");
          let startDate = new Date(startDateInput.value);
          let endDate = new Date(endDateInput.value);

          if (startDate >= endDate) {
            startDateInput.setCustomValidity("Start date must be before the end date.");
            endDateInput.setCustomValidity("End date must be after the start date.");
          } else {
            startDateInput.setCustomValidity("");
            endDateInput.setCustomValidity("");
          }

          // Custom validation for select field
          var cityInput = document.getElementById("city");
          if (cityInput.value === "") {
            cityInput.setCustomValidity("Please select a city.");
          } else {
            cityInput.setCustomValidity("");
          }

          form.classList.add("was-validated");
        });
      })();