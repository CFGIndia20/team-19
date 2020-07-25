import React from 'react';
import axios from "axios";
import CircularProgress from '@material-ui/core/CircularProgress';
import Header from './Header';


export default class Dashboard extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            topStates: null,
            loading: true,
            error: false
        }
    }
    componentDidMount = async () => {
        try {
            let topStates = await axios.get("http://cc48628bedf6.ngrok.io/api/get/states");
            console.log("Top states", topStates);
            this.setState({ topStates: topStates, loading: false });
        } catch (error) {
            console.log("Error", error.message);
            this.setState({loading: false, error: true});
            
        }


    }

    render() {
        let { topStates, loading, error } = this.state;
        return (
            <>
                <div className="site-wrap" id="home-section">

                    <Header />


                    <div className="site-blocks-cover" style={{ overflow: "hidden" }}>
                        <div className="container">
                            <div className="row align-items-center justify-content-center">

                                <div className="col-md-12" style={{ position: "relative" }} data-aos="fade-up" data-aos-delay="200">

                                    <img src={`${process.env.PUBLIC_URL}/assets/images/illustration.jpeg`} alt="illustration" className="img-fluid img-absolute" />

                                    <div className="row mb-4" data-aos="fade-up" data-aos-delay="200">
                                        <div className="col-lg-6 mr-auto">
                                            <h2>Make a measurable difference in quality of citizenship</h2>
                                            <p className="mb-5">We are a civic-tech non-profit, operating in Bengaluru, aimed at improving your quality of urban life. Scroll down to see where your state stands.</p>
                                            {/* <div>
                                                <a className="btn btn-primary mr-2 mb-2">Learn More</a>
                                            </div> */}
                                        </div>


                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>

                    <div className="site-blocks-cover" id="about-section" style={{ overflow: "hidden" }}>
                        <div className="abt-container">
                            <div className="citizen-container">
                                <div className="overlay-container" style={{ backgroundImage: `url('${process.env.PUBLIC_URL}/assets/images/citizens.jpeg')`, backgroundSize: "cover", }}></div>
                                <form action="#">
                                    <h3 style={{ textAlign: "left" }}>Best cities by their quality of citizenship</h3>

                                    {loading && <CircularProgress color="inherit" />}
                                    {!loading && error && <p>Sorry! Try after sometime...</p>}
                                    {topStates && topStates.data.data.map((state, index) => (
                                        <p className="input" key={index}><b>{state.cityName}</b><span style={{ float: "right" }}><b>{state.complaints}</b></span></p>
                                    ))}

                                    {/* <button style={{ marginTop: "5%" }} className="btn btn-primary">Checkout some cool graphsp</button> */}
                                </form>
                            </div>
                        </div>
                    </div>

                </div>
            </>
        )
    }
}