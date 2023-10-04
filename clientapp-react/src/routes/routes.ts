import Home from "../components/Home/Home";
import IRoute from "./IRoute";

export const ROUTES: IRoute[] = [{
    path: '/',
    name: "",
    component: Home,
    exact: true
}, {
    path: 'google-redirect/{state}',
    name: "",
    component: Home,
    exact: true
}]