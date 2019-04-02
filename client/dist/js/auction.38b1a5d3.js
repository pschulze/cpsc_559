(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["auction"],{"5aab":function(t,i,n){"use strict";var e=n("bf32"),r=n.n(e);r.a},bf32:function(t,i,n){},e261:function(t,i,n){"use strict";n.r(i);var e=function(){var t=this,i=t.$createElement,n=t._self._c||i;return n("div",{staticClass:"container d-flex justify-content-center text-center"},[n("div",[n("h1",[t._v(t._s(t.auction.name))]),n("h3",[t._v("$"+t._s(t.currentAmount))]),n("p",[t._v(t._s(t.endtimeString))]),n("div",{staticClass:"d-flex justify-content-center"},[n("BidForm",{attrs:{auction:t.auction}})],1),n("router-link",{attrs:{to:{name:"dog",params:{id:t.dog.id}}}},[n("h2",{staticClass:"mt-3"},[t._v(t._s(t.dog.name))])]),n("img",{attrs:{src:t.dog.imageUrl}}),n("div",{staticClass:"mb-3 mt-4 d-flex justify-content-center"},[t.loggedin&&t.dog.ownerId===t.userId?n("button",{staticClass:"btn btn-primary",attrs:{type:"button"},on:{click:function(i){return i.preventDefault(),t.$refs.editAuctionModal.showModal(i)}}},[t._v("\n        Edit Auction\n      ")]):t._e()]),n("Modal",{ref:"editAuctionModal",attrs:{title:"Edit Auction"},on:{hide:function(i){return t.$refs.editAuctionForm.reset()}}},[n("AuctionForm",{ref:"editAuctionForm",attrs:{auction:t.auction},on:{submitSuccess:function(i){return t.$refs.editAuctionModal.hideModal()}}})],1)],1)])},r=[],a=(n("c5f6"),n("cebc")),o=n("c1df"),s=n.n(o),u=n("2f62"),c=function(){var t=this,i=t.$createElement,n=t._self._c||i;return n("form",{ref:"form",staticClass:"form-inline",attrs:{novalidate:""},on:{submit:function(i){return i.preventDefault(),t.onSubmit(i)}}},[n("div",{staticClass:"form-group"},[n("label",{staticClass:"sr-only",attrs:{for:"bidFormInput"}},[t._v("Bid Amount")]),n("div",{staticClass:"input-group mb-2 mr-sm-2"},[t._m(0),n("input",{directives:[{name:"model",rawName:"v-model",value:t.amount,expression:"amount"}],staticClass:"form-control",attrs:{type:"number",id:"bidFormInput",placeholder:t.minimumBid,min:t.minimumBid,step:"0.01",required:""},domProps:{value:t.amount},on:{input:function(i){i.target.composing||(t.amount=i.target.value)}}})])]),n("button",{staticClass:"btn btn-primary mb-2",attrs:{type:"submit"}},[t._v("\n    "+t._s(t.submitLabel)+"\n  ")]),t.error?n("div",{staticClass:"alert alert-danger fade show",attrs:{role:"alert"}},[t._v("\n    "+t._s(t.error)+"\n  ")]):t._e()])},d=[function(){var t=this,i=t.$createElement,n=t._self._c||i;return n("div",{staticClass:"input-group-prepend"},[n("div",{staticClass:"input-group-text"},[t._v("$")])])}],m={name:"BidForm",props:{auction:{id:Number,startPrice:Number},submitLabel:{type:String,default:"Place Bid"}},data:function(){return{amount:null,error:null}},computed:Object(a["a"])({},Object(u["c"])(["userId"]),{minimumBid:function(){return this.auction.highestBid&&this.auction.highestBid.amount?this.auction.highestBid.amount+.01:this.auction.startPrice+.01}}),methods:{reset:function(){this.amount=null,this.$refs.form.classList.remove("was-validated")},checkForm:function(t){return!1!==this.$refs.form.checkValidity()||(this.$refs.form.classList.add("was-validated"),t.preventDefault(),t.stopPropagation(),!1)},createBid:function(){return this.$store.dispatch("auctions/placeBid",{auctionId:this.auction.id,bidderId:this.userId,amount:this.amount})},onSubmit:function(t){var i=this;this.error=null,this.checkForm(t)&&this.createBid().then(function(){i.$emit("sumbitSuccess"),i.reset()}).catch(function(t){t.data&&t.data.details&&"string"===typeof t.data.details?i.error=t.data.details:t.data&&t.data.errors&&"string"===typeof t.data.errors[0]?i.error=t.data.errors[0]:i.error=t.msg})}}},l=m,f=n("2877"),h=Object(f["a"])(l,c,d,!1,null,null,null),p=h.exports,b=n("714b"),g=n("a595"),v={name:"auction",components:{BidForm:p,Modal:b["a"],AuctionForm:g["a"]},computed:Object(a["a"])({},Object(u["c"])(["userId"]),Object(u["b"])({auctionById:"auctions/active/byId",dogById:"dogs/byId",loggedin:"loggedin"}),{auction:function(){return this.auctionById(Number(this.$route.params.id))},dog:function(){return this.dogById(this.auction.dogId)},currentAmount:function(){return this.auction.highestBid&&this.auction.highestBid.amount?this.auction.highestBid.amount:this.auction.startPrice},endtimeString:function(){if(!this.auction.expirationTime)return"Endtimes unavailable offline";var t=s()(),i=s()(this.auction.expirationTime);return t.diff(i)>0?"Ended "+i.fromNow():"Ends "+i.fromNow()}})},_=v,B=(n("5aab"),Object(f["a"])(_,e,r,!1,null,null,null));i["default"]=B.exports}}]);
//# sourceMappingURL=auction.38b1a5d3.js.map