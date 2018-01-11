(function () {
    var app = angular.module('store', []);

    app.controller('StoreController', function () {
        this.products = gems;
    });

    app.controller("PanelController", function () {
        this.tab = 1;

        this.selectTab = function (setTab) {
            this.tab = setTab;
        };
        this.isSelected = function (checkTab) {
            return this.tab === checkTab;
        };
    });

    var gems = [
        {
            name: 'Dodecahedron',
            price: 2.00,
            description: 'Some gems have hidden qualities beyond their luster, beyond their shine...Dodecha is one of those gems.',
            canPurchase: true,
            soldOut: false,
            images: [
                {
                    full: 'gem-full.jpg'
                },
                {
                    full: 'gem-full.jpg'
                }
            ],
            reviews: [{
                stars: 5,
                body: "I love this product",
                author: "joe@thomas.com"
            },
            {
                stars: 3,
                body: "better",
                author: "cassi@cassi.com"
            }
            ]
        },
        {
            name: 'Pentagonal Gem',
            price: 5.95,
            description: "Origin of the pentagonal gem is unknown. Hence its low value.",
            canPurchase: false,
            soldOut: false,
            images: [
                {
                    full: 'gem-full.jpg'
                },
                {
                    full: 'gem-full.jpg'
                }
            ],
            reviews: [{
                stars: 3,
                body: "ehh",
                author: "cassi@cassi.com"
            }]
        }
    ]
})();
