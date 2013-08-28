(function ($) {
    T5.extendInitializers(function () {

        function init(specs) {
            $('#button1').click(function (event) {
                // event.preventDefault();

                var params = { params: JSON.stringify({
                    a: $("#textfield").val(),
                    c: $("#checkbox").is(':checked')})
                };

                $.get(specs.button1_url, params)
                    .done(function(data) {
                        $("#responseDiv").text(data.response);
                    })
            })
        }

        return {
            ajaxParam : init
        };
    });

})(jQuery);

