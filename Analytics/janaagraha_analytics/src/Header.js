import React from "react";

const Header = () => {
    return (
        <>
            <div className="site-mobile-menu site-navbar-target">
                <div className="site-mobile-menu-header">
                    <div className="site-mobile-menu-close mt-3">
                        <span className="icon-close2 js-menu-toggle"></span>
                    </div>
                </div>
                <div className="site-mobile-menu-body"></div>
            </div>


            <header style={{position: "fixed"}} className="site-navbar py-4 js-sticky-header site-navbar-target" role="banner">

                <div className="container">
                    <div className="row align-items-center">

                        <div className="col-6 col-md-3 col-xl-4  d-block">
                            <img className="mb-0 site-logo" src={`${process.env.PUBLIC_URL}/assets/images/logo.jpeg`}></img>
                        </div>

                        <div className="col-12 col-md-9 col-xl-8 main-menu">
                            <nav className="site-navigation position-relative text-right" role="navigation">

                                <ul className="site-menu main-menu js-clone-nav mr-auto d-none d-lg-block ml-0 pl-0">
                                    <li><a href="#" className="nav-link">Home</a></li>
                                    <li><a href="#" className="nav-link">About</a></li>
                                    <li><a href="#" className="nav-link">Help</a></li>
                                    <li><a href="#" className="nav-link">Features</a></li>
                                    <li><a href="#" className="nav-link">Signup</a></li>
                                </ul>
                            </nav>
                        </div>


                        <div className="col-6 col-md-9 d-inline-block d-lg-none ml-md-0" ><a href="#" className="site-menu-toggle js-menu-toggle text-black float-right"><span className="icon-menu h3"></span></a></div>

                    </div>
                </div>

            </header>
        </>);
}

export default Header;