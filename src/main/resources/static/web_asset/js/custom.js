$(document).ready(function() {
	"use strict";
	//MEGA MENU
    $(".about-menu").hover(function() {
        $(".about-mm").fadeIn();
    });
    $(".about-menu").mouseleave(function() {
        $(".about-mm").fadeOut();
    });
    //MEGA MENU
    $(".admi-menu").hover(function() {
        $(".admi-mm").fadeIn();
    });
    $(".admi-menu").mouseleave(function() {
        $(".admi-mm").fadeOut();
    });
    //MEGA MENU
    $(".cour-menu").hover(function() {
        $(".cour-mm").fadeIn();
    });
    $(".cour-menu").mouseleave(function() {
        $(".cour-mm").fadeOut();
    });
    //SINGLE DROPDOWN MENU
    $(".top-drop-menu").on('click', function() {
        $(".man-drop").fadeIn();
    });
    $(".man-drop").mouseleave(function() {
        $(".man-drop").fadeOut();
    });
    $(".wed-top").mouseleave(function() {
        $(".man-drop").fadeOut();
    });

    //SEARCH BOX
    $("#sf-box").on('click', function() {
        $(".sf-list").fadeIn();
    });
    $(".sf-list").mouseleave(function() {
        $(".sf-list").fadeOut();
    });
    $(".search-top").mouseleave(function() {
        $(".sf-list").fadeOut();
    });
    $('.sdb-btn-edit').hover(function() {
        $(this).text("Click to edit my profile");
    });
    $('.sdb-btn-edit').mouseleave(function() {
        $(this).text("edit my profile");
    });
    //MOBILE MENU OPEN
    $(".ed-micon").on('click', function() {
        $(".ed-mm-inn").addClass("ed-mm-act");
    });
    //MOBILE MENU CLOSE
    $(".ed-mi-close").on('click', function() {
        $(".ed-mm-inn").removeClass("ed-mm-act");
    });

    //GOOGLE MAP IFRAME
    $('.map-container').on('click', function() {
        $(this).find('iframe').addClass('clicked')
    }).on('mouseleave', function() {
        $(this).find('iframe').removeClass('clicked')
    });

    $('#status').fadeOut();
    $('#preloader').delay(350).fadeOut('slow');
    $('body').delay(350).css({
        'overflow': 'visible'
    });


	//MATERIALIZE SLIDER
    $('.slider').slider();

    //AUTO COMPLETE CITY SELECT
    $('#select-city,#select-city-1,#select-city-2,#select-city-3,#select-city-4,#select-city-5.autocomplete').autocomplete({
        data: {
            "New York": null,
            "California": null,
            "Illinois": null,
            "Texas": null,
            "Pennsylvania": null,
            "San Diego": null,
            "Los Angeles": null,
            "Dallas": null,
            "Austin": null,
            "Columbus": null,
            "Charlotte": null,
            "El Paso": null,
            "Portland": null,
            "Las Vegas": null,
            "Oklahoma City": null,
            "Milwaukee": null,
            "Tucson": null,
            "Sacramento": null,
            "Long Beach": null,
            "Oakland": null,
            "Arlington": null,
            "Tampa": null,
            "Corpus Christi": null,
            "Greensboro": null,
            "Jersey City": null
        },
        limit: 8, // The max amount of results that can be shown at once. Default: Infinity.
        onAutocomplete: function(val) {
            // Callback function when value is autcompleted.
        },
        minLength: 1, // The minimum length of the input for the autocomplete to start. Default: 1.
    });

    $('#select-search,#select-search-1,#select-search-2.autocomplete').autocomplete({
        data: {
            "Top Honeymoon Packages in India": 'images/icon/7.png',
            "Family Package": 'images/icon/8.png',
            "World Tour Package": 'images/icon/9.png',
            "Hill Stations": 'images/icon/10.png',
            "America Holidays": 'images/icon/11.png',
            "Germany Holidays": 'images/icon/12.png',
            "Hong Kong Holidays": 'images/icon/13.png',
            "Europe Holidays": 'images/icon/14.png',
            "France Holidays": 'images/icon/15.png',
            "Switzerland,Bali,Thailand Package": 'images/icon/16.png',
            "Maldives,Malaysia,Pattaya Package": 'images/icon/17.png',
            "Dubai Packages": 'images/icon/18.png',
            "Europe Tour Packages": 'images/icon/19.png',
            "USA Tour Packages": 'images/icon/20.png',
            "Mexico City, Mexico": 'images/icon/21.png',
            "Seoul, South Korea": 'images/icon/22.png',
            "Ljubljana, Slovenia": 'images/icon/23.png',
            "Wroclaw, Poland": 'images/icon/24.png',
            "Nashville, USA": 'images/icon/25.png',
            "Amsterdam, the Netherlands": 'images/icon/26.png',
            "First World Hotel": 'images/icon/27.png',
            "MGM Grand Las Vegas Hotel": 'images/icon/28.png',
            "CityCenter": 'images/icon/29.png',
            "Holiday Hotel Inn": 'images/icon/13.png',
            "Tour and Travel Packages": 'images/icon/14.png',
            "City Seight Seeings": 'images/icon/15.png',
			"Mandarin Oriental, Hong Kong, China": 'images/icon/25.png',
            "Trump International Hotel & Tower, New York, United States": 'images/icon/26.png',
            "First World Hotel": 'images/icon/27.png',
            "MGM Grand Las Vegas Hotel": 'images/icon/28.png',
            "CityCenter": 'images/icon/29.png',
            "Holiday Hotel Inn": 'images/icon/13.png',
            "Tour and Travel Packages": 'images/icon/14.png',
            "City Seight Seeings": 'images/icon/15.png'
        },
        limit: 8, // The max amount of results that can be shown at once. Default: Infinity.
        onAutocomplete: function(val) {
            // Callback function when value is autcompleted.
        },
        minLength: 1, // The minimum length of the input for the autocomplete to start. Default: 1.
    });

	// DYNAMIC GUEST ADD & DELETE
	var counter = 0;


	//Once add button is clicked
    $(document).on('click', '.add-guest-btn', function(e){
		counter++;

		var fieldHTML = `<div class="row">
			<div class="col-md-1">
				<div class="input-field col s12">
					<div class="guest-number">
						Guest <span class="number">`+ (counter+1) +`</span>
					</div>
				</div>
			</div>
			<div class="col-md-2">
				<div class="input-field col s12">
					<select name="guests[`+ counter +`].nameInit" id="g-title-`+ counter +`">
						<option value="" disabled selected>Title</option>
						<option value="Mr.">Mr.</option>
						<option value="Mrs.">Mrs.</option>
						<option value="Ms.">Ms.</option>
					</select>
				</div>
			</div>
			<div class="col-md-4">
				<div class="input-field col s12">
					<input type="text"  class="validate" name="guests[`+ counter +`].name" id="g-fullname-`+ counter +`" required>
					<label>Full Name</label>
				</div>
			</div>
			<div class="col-md-1">
				<div class="input-field col s12">
					<input type="text"  class="validate" name="guests[`+ counter +`].age" id="g-age-`+ counter +`" required>
					<label>Age</label>
				</div>
			</div>
			<div class="col-md-3">
				<div class="input-field col s12">
					<div class="custom-radio">
						<input type="radio" id="m-gender-`+ counter +`" name="guests[`+ counter +`].gender" value="Male" checked>
						<label for="m-gender-`+ counter +`">Male</label>
					</div>
					<div class="custom-radio">
						<input type="radio" id="f-gender-`+ counter +`" name="guests[`+ counter +`].gender" Value="Female">
						<label for="f-gender-`+ counter +`">Female</label>
					</div>
				</div>
			</div>
			<div class="col-md-1">
				<div class="input-field col s12">
					<button type="button" class="remove-guest-btn"><i class="fa fa-times"></i></button>
				</div>
			</div>
		</div>`;


        $(".guest-inputs-wrapper").append(fieldHTML);
		$('select').select2();
    });

    //Once remove button is clicked
    $(document).on('click', '.remove-guest-btn', function(e){
        e.preventDefault();
        $(this).closest('.row').remove();
		counter--;
    });

	$(document).on('change', '#c-guest', function(e){
		if($(this).is(':checked')) {
			var guest_title = $( "#c-title" ).val();
			var guest_firstname = $( "#c-firstname" ).val();
			var guest_lastname = $( "#c-lastname" ).val();
			$('#g-title-0 option[value="'+guest_title+'"]').prop('selected', true).change();
			$('select').select2();

			$( "#g-fullname-0" ).val(guest_firstname + " " + guest_lastname);
			if(guest_firstname != '' || guest_lastname != '') {
				$( "#g-fullname-0" ).addClass("valid");
				$( "#g-fullname-0 + label" ).addClass("active");
			}
		} else {
			$('#g-title-0 option[value=""]').prop('selected', true).change();
			$('select').select2();

			$( "#g-fullname-0" ).val("");
			$( "#g-fullname-0" ).removeClass("valid");
			$( "#g-fullname-0 + label" ).removeClass("active");
		}

    });

	$(document).on('click', '.confirm-btn-2', function(e){
        e.preventDefault();
		var contact_title = "";
		var contact_country_code = "";
		if($( "#c-title" ).val() !== null) {
			contact_title = $( "#c-title option:selected" ).text();
		}
		var contact_firstname = $( "#c-firstname" ).val();
		var contact_lastname = $( "#c-lastname" ).val();
		var contact_email = $( "#c-email" ).val();
		if($( "#c-country-code" ).val() !== null) {
			contact_country_code = $( "#c-country-code" ).val();
		}
		var contact_phone = $( "#c-phone" ).val();

		var all_guest_pass = false;
		$( ".guest-info-details table tbody" ).empty();

		if(contact_title && contact_country_code && contact_firstname && contact_lastname && contact_email && isEmail(contact_email) && contact_phone) {
			$( ".contact-info-details .name" ).text(contact_title + " " + contact_firstname + " " + contact_lastname);
			$( ".contact-info-details .email" ).text(contact_email);
			$( ".contact-info-details .phone" ).text(contact_country_code + " " + contact_phone);

			$( ".guest-inputs-wrapper .row" ).each(function( index ) {
				//index++;

				var guest_title = "";
				if($( "#g-title-" + index ).val() !== null) {
					guest_title = $( "#g-title-" + index + " option:selected" ).text();
				}
				var guest_fullname = $( "#g-fullname-" + index  ).val();
				var guest_age = $( "#g-age-" + index ).val();
				var guest_gender = $( "input[name='guests["+ index + "].gender']:checked" ).val();

				if(guest_title && guest_fullname && guest_age) {
					$( ".guest-info-details table tbody" ).append(`
						<tr>
							<td>`+(index+1)+`</td>
							<td>`+guest_title + " " + guest_fullname +`</td>
							<td>`+guest_age+`</td>
							<td>`+guest_gender+`</td>
						</tr>
					`);
					all_guest_pass = true;

				} else {
					if(guest_title === "") {
						$( "#g-title-" + index ).parent().addClass("invalid");
					}else {
						$( "#g-title-" + index ).parent().removeClass("invalid");
					}
					if(guest_fullname === "") {
						$( "#g-fullname-" + index ).addClass("invalid");
					}else {
						$( "#g-fullname-" + index ).removeClass("invalid");
					}
					if(guest_age === "") {
						$( "#g-age-" + index ).addClass("invalid");
					} else {
						$( "#g-age-" + index ).removeClass("invalid");
					}
					all_guest_pass = false;
				}
			});

			if(all_guest_pass) {
				$( ".edit-guest-btn" ).addClass("active");
				$('#cart-hotel-details').collapse('hide');
				$('#cart-guest-details').collapse('hide');
				$('#cart-payment-details').collapse('show');
			}

		} else {
			if(contact_title === "") {
				$( "#c-title" ).parent().addClass("invalid");
			}else {
				$( "#c-title" ).parent().removeClass("invalid");
			}
			if(contact_country_code === "") {
				$( "#c-country-code" ).parent().addClass("invalid");
			}else {
				$( "#c-country-code" ).parent().removeClass("invalid");
			}
			if(contact_firstname === "") {
				$( "#c-firstname" ).addClass("invalid");
			} else {
				$( "#c-firstname" ).removeClass("invalid");
			}
			if(contact_lastname === "") {
				$( "#c-lastname" ).addClass("invalid");
			}else {
				$( "#c-lastname" ).removeClass("invalid");
			}
			if(contact_email === "" || !isEmail(contact_email)) {
				$( "#c-email" ).addClass("invalid");
			}else {
				$( "#c-email" ).removeClass("invalid");
			}
			if(contact_phone === "") {
				$( "#c-phone" ).addClass("invalid");
			}else {
				$( "#c-phone" ).removeClass("invalid");
			}
			all_guest_pass = false;
		}
    });

	$(document).on('click', '.edit-guest-btn', function(e){
        $(this).removeClass('active');

		$( ".guest-info-details table tbody" ).empty();

		$('#cart-hotel-details').collapse('show');
		$('#cart-guest-details').collapse('show');
		$('#cart-payment-details').collapse('hide');
    });

    $('.header-nav-tab.nav-tabs a').on('shown.bs.tab', function(e){
        var current_link = $(e.target).attr('href').substring(1);
        $('#header-search-area').removeClass();
        $( "#header-search-area" ).addClass(current_link);
    });

    $(".custom-radio input[type='radio']").click(function(){
        if ($("#f-round-trip-0").prop("checked")) {
            $(".search-input-option-area #to").removeAttr("disabled");
        } else {
            $(".search-input-option-area #to").attr("disabled", "");
        }

        if ($("#b-round-trip-0").prop("checked")) {
            $(".search-input-option-area #to-3").removeAttr("disabled");
        } else {
            $(".search-input-option-area #to-3").attr("disabled", "");
        }

        if ($("#c-round-trip-0").prop("checked")) {
            $(".search-input-option-area #to-4").removeAttr("disabled");
        } else {
            $(".search-input-option-area #to-4").attr("disabled", "");
        }
    });

    var active_link = $('.header-nav-tab.nav-tabs li.active a').attr('href');
    $('.header-search-tab-wrapper .tab-content').find(active_link).addClass("in active");

   var form = $("#merchant-advanced-form").show();
   form.steps({
       headerTag: "h3",
       bodyTag: "fieldset",
       transitionEffect: "slideLeft",
       titleTemplate: '<span class="number">#index#.</span> <span class="step-title">#title#</span>',
       labels: {
           finish: "Submit",
           next: "Continue",
           previous: "Back"
       },
       onStepChanging: function (event, currentIndex, newIndex) {
           if (currentIndex > newIndex) {
               return true;
           }

           if (currentIndex < newIndex) {
               form.find(".body:eq(" + newIndex + ") label.error").remove();
               form.find(".body:eq(" + newIndex + ") .error").removeClass("error");
           }
           form.validate().settings.ignore = ":disabled,:hidden";
           return form.valid();
       },
       onStepChanged: function (event, currentIndex, priorIndex) {

       },
       onFinishing: function (event, currentIndex) {
           form.validate().settings.ignore = ":disabled";
           return form.valid();
       },
       onFinished: function (event, currentIndex) {
           form.submit();
       }
   }).validate({
       errorPlacement: function errorPlacement(error, element) { element.before(error); },
       rules: {

       }
   });

    $( ".file-path-wrapper" ).after( `
        <div class='file-preview-wrapper'>
            <div class='file-counter'><span>0</span> File(s) Selected</div>
            <div class='file-preview'></div>
        </div>
    ` );
    $('.file-path-wrapper input[type=file]').on("change", previewImages);

    //MATERIALIZE SELECT DROPDOWN
    //$('select').material_select();
    $('select').select2();

});


function previewImages() {

    var preview = $(this).closest('.input-field').find('.file-preview').empty();
    $('.file-preview-wrapper .file-counter span').text(this.files.length);
    if (this.files) $.each(this.files, readAndPreview);

    function readAndPreview(i, file) {

        if (!/\.(jpe?g|png|gif)$/i.test(file.name)){
            return alert(file.name +" is not an image");
        }
        if (file.size > 1022976){
            return alert(file.name +" is too large. Max upload size 999 kb");
        }
        var file_size = (file.size / 1024).toFixed(2);
        var reader = new FileReader();

        $(reader).on("load", function() {
            //preview.append($("<img/>", {src:this.result, height:100}));
            preview.append("<div class='single-file-preview'><img src='"+ this.result +"' /><div class='single-file-info'><span class='single-file-name'>"+ file.name +"</span><span class='single-file-size'>"+ file_size +" kb</span></div></div>");
        });

        reader.readAsDataURL(file);

    }

}
function isEmail(email) {
  var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
  return regex.test(email);
}
function myFunction() {
    var input, filter, table, tr, td, i;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("myTable");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[1];
        if (td) {
            if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

//DATE PICKER
$(function() {
    var dateFormat = "mm/dd/yy",
        from = $("#from,#from-1,#from-2,#from-3,#from-4,#from-5")
        .datepicker({
            changeMonth: false,
            numberOfMonths: 1
        })
        .on("change", function() {
            to.datepicker("option", "minDate", getDate(this));
        }),
        to = $("#to,#to-1,#to-2,#to-3,#to-4,#to-5").datepicker({
            changeMonth: false,
            numberOfMonths: 1
        })
        .on("change", function() {
            from.datepicker("option", "maxDate", getDate(this));
        });

    function getDate(element) {
        var date;
        try {
            date = $.datepicker.parseDate(dateFormat, element.value);
        } catch (error) {
            date = null;
        }

        return date;
    }
});
