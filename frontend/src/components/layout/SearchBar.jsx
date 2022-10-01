export default function SearchBar() {
    return (
        <div className="flex items-center">
            <div className="flex border border-sky-400 rounded">
                <input
                    type="text"
                    className="block w-full px-4 py-2 text-white bg-gray-900 border rounded-md
                    focus:border-sky-400 focus:ring-sky-300 focus:outline-none focus:ring
                    focus:ring-opacity-40"
                    placeholder="Search..."
                />
                <button className="px-4 text-white bg-sky-600 border-l rounded ">
                    Search
                </button>
            </div>
        </div>
    );
}