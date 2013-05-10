var Mangrove = Mangrove || {};


$(document).ready(function(){
	$('#about-container').css('display', 'none');
	$('#about-container').fadeIn(2000);
	
	
	$("#leftArrow").click(function(){
	    $("#divId").scrollLeft(Math.max(0, $("#divId").scrollLeft() - 100));
	});
	
	$('#services-button').click(function(){
		var aTag = $("#services-container");
	    $('html,body').animate({scrollTop: aTag.offset().top},'slow');
	});
	
	$('#about-button').click(function(){
		var aTag = $("#about-container");
	    $('html,body').animate({scrollTop: aTag.offset().top},'slow');
	});
	
	$('#contact-button').click(function(){
		var aTag = $("#contact-container");
	    $('html,body').animate({scrollTop: aTag.offset().top},'slow');
	});
	
	$('#arrow-top1').click(function(){
	    $('html,body').animate({scrollTop: 0},'slow');
	});
	$('#arrow-top2').click(function(){
	    $('html,body').animate({scrollTop: 0},'slow');
	});
	$('#arrow-top3').click(function(){
	    $('html,body').animate({scrollTop: 0},'slow');
	});
	
	console.log();
	//$('#design-thyself').css('opacity', '0.1');
	$('#design-thyself').fadeIn(5000);
	$('#design-thyself').animate({left:$('#brand').position().left+'px', opacity: 1.0});
		
	$(window).resize(function(){
		$('#design-thyself').animate({left:$('#brand').position().left+'px'});
	});

	$('#closeThanks').click(function(){
		 $('html,body').animate({scrollTop: 0},'slow');
		 $('#contact-form').find("input[type=text], textarea").val("");
	});

	$('#closeThanksx').click(function(){
		 $('html,body').animate({scrollTop: 0},'slow');
		 $('#contact-form').find("input[type=text], textarea").val("");
	});
		
	console.log("executed...");
	$('#contact-form').on("submit", function(event){
		console.log("clicked form...");
		event.preventDefault();
		
		
		var contactInformation = {};
		contactInformation.name = $('#contact-form input[name=name]').val();
		contactInformation.email = $('#contact-form input[name=contactEmail]').val();
		contactInformation.companyName = $('#contact-form input[name=companyName]').val(); 
		contactInformation.website = $('#contact-form input[name=website]').val();
		contactInformation.reason = $('#contact-form textArea[name=reason]').val();
		console.log(contactInformation);
		contactInformation = JSON.stringify(contactInformation);

		var url = "/mangrove/rest/user/contact";
		$.ajax(url, {
			crossDomain: true,
			contentType: "application/json",
			data: contactInformation,
			type: "POST",
			success: function(data) {
				console.log("data was saved correctly");
				$('#launchThanksModal').click();
				//var myData = $.parseJSON(data);
			},
			error: function(xhr, textStatus, error){
							  console.log(xhr.statusText);
							  console.log(textStatus);
							  console.log(error);
			}
		});
		
		return false;
	});
	
	
});