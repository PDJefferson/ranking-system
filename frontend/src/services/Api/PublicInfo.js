import {API_ENUM} from "../../scripts/api-endpoints";

export async function getTopPlayers(pageNumber = 0, size = 5, sortBy = 'power' ) {
    const response = await fetch(
        `${API_ENUM.API_ENDPOINT + API_ENUM.GET_TOP_PLAYERS}?page=${pageNumber}&sortBy=${sortBy}&size=${size}`
    );
    const data = await response.json();

    if (!response.ok) {
        throw new Error(response.statusText);
    }

    return data?.content;
}

export async function getTopAlliances(pageNumber = 0, sortBy = 'power', size = 5) {
    const response = await fetch(
        `${API_ENUM.API_ENDPOINT + API_ENUM.GET_TOP_ALLIANCES}?page=${pageNumber}&sortBy=${sortBy}&size=${size}`
    );
    if (!response.ok) {
        throw new Error(response.statusText);
    }
    const data = await response.json();

    return data?.content;
}