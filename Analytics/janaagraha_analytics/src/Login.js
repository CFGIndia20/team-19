import React from 'react';


export default class Login extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            username: "",
            password: "",
            email: "",
            isSignIn: true
        }
    }

    handleLogin = () => {
        this.props.history.push("/dashboard");
    }

    handleRegister = () => {

    }

    render() {
        let { isSignIn } = this.state;
        let className = isSignIn ? "" : "right-panel-active";
        return (
            <div className="login">
                <div className={"login-container " + className} id="container">
                    <div className="form-container sign-up-container">
                        <form action="#">
                            <h1>Create Account</h1>
                            <input type="text" placeholder="Name" onChange={(e) => this.setState({ username: e.target.value })} />
                            <input type="email" placeholder="Email" onChange={(e) => this.setState({ email: e.target.value })} />
                            <input type="password" placeholder="Password" onChange={(e) => this.setState({ password: e.target.value })} />
                            <button onClick={this.handleRegister}>Sign Up</button>
                        </form>
                    </div>
                    <div className="form-container sign-in-container">
                        <form action="#">
                            <h1>Sign in</h1>
                            <input type="email" placeholder="Email" onChange={(e) => this.setState({ email: e.target.value })} />
                            <input type="password" placeholder="Password" onChange={(e) => this.setState({ password: e.target.value })} />
                            <button onClick={this.handleLogin}>Sign In</button>
                        </form>
                    </div>
                    <div className="overlay-container">
                        <div className="overlay">
                            <div className="overlay-panel overlay-left">
                                <h1>Welcome Back!</h1>
                                <p>To keep connected with us please login with your personal info</p>
                                <button className="ghost" id="signIn" onClick={() => this.setState({ isSignIn: true })}>Sign In</button>
                            </div>
                            <div className="overlay-panel overlay-right">
                                <h1>Hey, there!</h1>
                                <p>Enter your details and start journey with us</p>
                                <button className="ghost" id="signUp" onClick={() => this.setState({ isSignIn: false })}>Sign Up</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

}