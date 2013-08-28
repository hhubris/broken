(function ($) {

    T5.extendInitializers(function() {

        var _specs;

        function init(specs) {
            _specs = specs;

            Broken1.test1(function(data) {
                $("#hello").html(data);
            });

            Broken1.getDs(function(data) {
                update_display(data);
            });

            $("#clickMe").click(function(elem) {

                var data = { id: 8, name: 'Lucy Brown', numbers: [1, 3, 5]};

                Broken1.updateDs(data, function(result) {
                    console.log(result);
                    update_display(result);
                })
            });
        }

        function update_display(data) {
            $("#id").html(data.id);
            $("#name").html(data.name);
            $("#numbers").html(data.numbers.join(","));
        }

        return {
            DwrDemo : init
        }
    });

})(jQuery);