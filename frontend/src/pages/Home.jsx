import CustomCard from '../components/ui/Card';
import homeLogo from "../images/home-image.jpg";
import {useEffect} from "react";
import useHttp from "../hooks/use-http";
import {getTopAlliances, getTopPlayers} from "../services/Api/PublicInfo";
import {useSelector} from "react-redux";


export default function Home() {
    const status = useSelector(state => state.http.status);
    const {
        sendRequest,
        data
    } = useHttp(getTopPlayers, getTopAlliances, true);

    useEffect(() => {
        sendRequest();
    }, [sendRequest])

    if(status === 404) {
        return <p>Something went wrong</p>

    }

    if( data != null){
        const [players, alliances] = data;
        return (
            <>
                <div className="mb-4">
                    <img
                        src={homeLogo}
                        className="h-full w-full"
                        alt="image-blur-shadow"
                    />
                </div>
                <div className="grid grid-cols-2 gap-4">
                    <CustomCard title="Players" object={players}/>
                    <CustomCard title="Alliances" object={alliances}/>
                </div>
            </>

        )
    }

}