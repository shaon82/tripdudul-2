$(document).ready(function() {
    "use strict";

    //LEFT MOBILE MENU OPEN
    $(".atab-menu").on('click', function() {
        $(".sb2-1").css("left", "0");
        $(".btn-close-menu").css("display", "inline-block");
    });

    //LEFT MOBILE MENU CLOSE
    $(".btn-close-menu").on('click', function() {
        $(".sb2-1").css("left", "-350px");
        $(".btn-close-menu").css("display", "none");
    });



    //MATERIAL COLLAPSIBLE
    $('.collapsible').collapsible();

    //MATERIAL CHIP COMMON
    $('.chips').material_chip();
    $('.chips-initial').material_chip({
        data: [{
            tag: 'Apple',
        }, {
            tag: 'Microsoft',
        }, {
            tag: 'Google',
        }],
    });



    //MATERIAL CHIP PLACEHOLDER
    $('.chips-placeholder').material_chip({
        placeholder: 'Enter a tag',
        secondaryPlaceholder: '+Amini (press enter)',
    });

    //MATERIAL CHIP AUTO-COMPLETE
    $('.chips-autocomplete').material_chip({
        autocompleteOptions: {
            data: {
                'Apple': null,
                'Microsoft': null,
                'Google': null
            },
            limit: Infinity,
            minLength: 1
        }
    });

    $(".package-images-carousel").owlCarousel({
        loop: true,
        margin: 0,
        items: 1,
        stagePadding: 0,
        autoplay: false,
        lazyLoad: true,
        dots: true,
        dotsData: false,
        animateOut: "fadeOut",
        animateIn: "fadeIn",
        nav: false,
        navText: [
            "<i class='fa fa-chevron-left'></i>",
            "<i class='fa fa-chevron-right'></i>"
        ],
        responsiveClass: true,
        responsive: {
            0: {},
            480: {},
            768: {},
            992: {},
            1200: {}
        }
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
						<option value="1">Mr.</option>
						<option value="2">Mrs.</option>
						<option value="3">Ms.</option>
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
			$('#g-title-0 option[value='+guest_title+']').prop('selected', true).change();
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



	$(document).on('click', '.edit-guest-btn', function(e){
        $(this).removeClass('active');

		$( ".guest-info-details table tbody" ).empty();

		$('#cart-hotel-details').collapse('show');
		$('#cart-guest-details').collapse('show');
		$('#cart-payment-details').collapse('hide');
    });


    $(document).on('change', '#add-booking-type', function(e){
        var id = $(this).val();

        getRoomsByCategoryId(id);
    });

    var user_working_time_edit = 0;
    $(".working-hours-input").slideUp(600);

    $(document).on('click', '.user-working-hours-edit-btn', function(e){
        if(user_working_time_edit == 0) {
            $(".show-working-hours").slideUp(600);
            $(".working-hours-input").slideDown(600);
            $(this).text('Save');
            $(this).attr('type', 'submit');
            user_working_time_edit = 1;
        } else {
            $(".working-hours-input").slideUp(600);
            $(".show-working-hours").slideDown(600);
            $(this).text('Edit');
            $(this).attr('type', 'button');
            user_working_time_edit = 0;
        }
    });


    var user_payment_show = 0;
    $(".pay-salary-form").hide(600);

    $(document).on('click', '.s-pay', function(e){
        if(user_working_time_edit == 0) {
            $(".pay-salary-form").show(600);
            $(".s-pay").hide(600);
            user_payment_show = 1;
        }
    });



    var admin_hotel_percentage = 0;
    $(".percentage-edit-form").hide(600);

    $(document).on('click', '.percentage-edit-btn', function(e){
        if(user_working_time_edit == 0) {
            $(".percentage-edit-form").show(600);
            $(".percentage-edit-btn").hide(600);
            user_payment_show = 1;
        }
    });

    $('.custom-datatable').DataTable();

    //MATERIAL SELECT BOX
    //$('select').material_select();



    var form = $("#hotel-advanced-form").show();
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
            // Allways allow previous action even if the current form is not valid!
            if (currentIndex > newIndex) {
                return true;
            }

            // Needed in some cases if the user went back (clean up)
            if (currentIndex < newIndex) {
                // To remove error styles
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

    $('.hotel-details-image-carousel').owlCarousel({
        loop: true,
        dots: true,
   		autoplay:false,
        margin:0,
        items:1,
        //nav: true,
        //navText: ['<i class="fa fa-angle-left"></i>','<i class="fa fa-angle-right"></i>'],
        responsiveClass:true,
        autoplayTimeout:5000,
		autoplayHoverPause:true,
        responsive:{
            0:{

            },
            992:{

            }
        }
    });

    $('.modal').on('shown.bs.modal', function (event) {
        $('select').select2();
    });

    $(document).on('click', '.meal-edit-btn', function(e){
        var meal_name = $(this).closest('tr').find('.meal-name').text();
        var meal_category = $(this).closest('tr').find('.meal-category').text();
        var meal_price = $(this).closest('tr').find('.meal-price').text();
        $('#edit-meal-details').on('shown.bs.modal', function (event) {
            $(this).find('label').addClass('active');
            $(this).find('#mealName').val(meal_name);
            $(this).find('#mealPrice').val(meal_price);
            $('#mealCategory option[value='+meal_category+']').prop('selected', true).change();
            $('select').select2();
        });
    });


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
function getRoomsByCategoryId(id, handleData){
   $.ajax({
        url:"/merchant/find/rooms/"+id,
        type: 'GET', //POST or GET
        data: id,
        success:function(result){
            $('#add-room-list').find('option').remove();
            $.each(result, function (i, item) {
                $('#add-room-list').append($('<option>', {
                    value: item.id,
                    text : item.roomNumber
                }));
            });
            $('select').select2();
        }
    });
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
